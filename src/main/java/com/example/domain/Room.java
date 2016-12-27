package com.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author Roman Horilyi
 */
@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    private LocalTime openTime;
    private LocalTime closeTime;

    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;
}
