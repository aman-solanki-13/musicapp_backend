package com.appoloComputers.MusicApp_Backend.Repositories;

import com.appoloComputers.MusicApp_Backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
