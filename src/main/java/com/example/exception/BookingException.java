package com.example.exception;

/**
 * @author Roman Horilyi
 */
public class BookingException extends Exception {
    public BookingException() {
        super("Busy...");
    }
}
