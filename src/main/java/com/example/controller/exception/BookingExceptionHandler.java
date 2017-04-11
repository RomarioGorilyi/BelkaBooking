package com.example.controller.exception;

import com.example.exception.BookingException;
import com.example.exception.RoomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Roman Horilyi
 */
@ControllerAdvice
@Slf4j
public class BookingExceptionHandler extends BaseExceptionHandler {

    public BookingExceptionHandler() {
        super(log);
        registerMapping(
                BookingException.class,
                "BOOKING_ERROR",
                "BookingException occurred",
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleRoomNotFoundException(RoomNotFoundException e) {
        log.info("RoomNotFoundException occurred for Room=" + e.getRoomTitle());
    }
}
