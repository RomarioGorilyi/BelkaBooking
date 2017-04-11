package com.example.controller.exception;

import com.example.exception.BookingException;
import com.example.exception.RoomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Roman Horilyi
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException sqlEx, WebRequest webRequest) {
        log.info("SQLException occurred:: URL=" + webRequest.getContextPath());
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(sqlEx, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest); // INTERNAL_SERVER_ERROR = 500
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occurred")
    public void handleIOException() {
        log.error("IOException handler executed");
    }
}
