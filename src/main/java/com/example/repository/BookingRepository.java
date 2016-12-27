package com.example.repository;

import com.example.domain.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Roman Horilyi
 */
public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
}
