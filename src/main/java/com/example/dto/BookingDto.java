package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Roman Horilyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private String username;
    private String roomTitle;
    private LocalDateTime dateTime;
    private Long minutes;
}
