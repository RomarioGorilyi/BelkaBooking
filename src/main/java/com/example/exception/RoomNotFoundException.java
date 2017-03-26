package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Roman Horilyi
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Room Not Found") // 404
public class RoomNotFoundException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    private String roomTitle;

    public RoomNotFoundException(String roomTitle) {
        super("RoomNotFoundException with roomTitle=" + roomTitle);
        this.roomTitle = roomTitle;
    }

    public String getRoomTitle() {
        return roomTitle;
    }
}
