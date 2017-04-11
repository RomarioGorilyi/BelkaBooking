package com.example.controller;

import com.example.domain.Booking;
import com.example.domain.Room;
import com.example.domain.User;
import com.example.dto.BookingDto;
import com.example.exception.BookingException;
import com.example.exception.RoomNotFoundException;
import com.example.repository.BookingRepository;
import com.example.repository.RoomRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Roman Horilyi
 */
@RepositoryRestController
public class BookingController {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingController(RoomRepository roomRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(value = "/bookings/{roomTitle}", method = RequestMethod.GET, produces = "application/json")
    @Cacheable(value = "bookingsForRoom", key = "#roomTitle")
    public List<Booking> getBookingsForRoom(@PathVariable String roomTitle) throws RoomNotFoundException {
        Room room = roomRepository.findByTitle(roomTitle);
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException(roomTitle);
        }
        return bookingRepository.findByRoom(room);
    }

    @RequestMapping(value = "/bookings/newBooking", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void makeBooking(@RequestBody BookingDto bookingDto) throws BookingException {
        LocalDateTime dateTime = bookingDto.getDateTime();
        Duration bookingDuration = Duration.ofMinutes(bookingDto.getMinutes());

        if (!canBeBooked(dateTime, bookingDuration)) {
            throw new BookingException();
        }

        Room room = roomRepository.findByTitle(bookingDto.getRoomTitle());
        User user = userRepository.findByUsername(bookingDto.getUsername());
        bookingRepository.save(createBooking(user, room, dateTime, bookingDuration));
    }

    private boolean canBeBooked(LocalDateTime bookingStart, Duration bookingDuration) {
        boolean canBeBooked = true;
        LocalDateTime bookingEnd = bookingStart.plusMinutes(bookingDuration.toMinutes());

        for (Booking currentBooking : bookingRepository.findAll()) {
            LocalDateTime currentBookingStart = currentBooking.getDateTime();
            LocalDateTime currentBookingEnd = currentBookingStart.plusMinutes(bookingDuration.toMinutes());

            if ((bookingStart.isAfter(currentBookingStart) && bookingStart.isBefore(currentBookingEnd))
                    || (bookingEnd.isAfter(currentBookingStart) && bookingEnd.isBefore(currentBookingEnd))) {
                canBeBooked = false;
                break;
            }
        }

        return canBeBooked;
    }

    private Booking createBooking(User user, Room room, LocalDateTime dateTime, Duration duration) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setDateTime(dateTime);
        booking.setDuration(duration);

        return booking;
    }

    @RequestMapping(value = "/session")
    @ResponseStatus(HttpStatus.OK)
    public void manageSessions(HttpSession session) {
        System.out.println(session.getId());
    }
}
