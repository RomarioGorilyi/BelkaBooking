package com.example.repository;

import com.example.domain.Booking;
import com.example.domain.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

/**
 * @author Roman Horilyi
 */
public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
    ArrayList<Booking> findByRoom(Room room);
}
