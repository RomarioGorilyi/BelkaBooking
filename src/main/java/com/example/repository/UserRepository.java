package com.example.repository;

import com.example.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Roman Horilyi
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
