package com.example.repository;

import com.example.domain.Room;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Roman Horilyi
 */
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByTitle(String title);
}
