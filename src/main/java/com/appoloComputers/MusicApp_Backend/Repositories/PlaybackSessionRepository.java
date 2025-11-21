package com.appoloComputers.MusicApp_Backend.Repositories;


import com.appoloComputers.MusicApp_Backend.Entities.User;
import com.appoloComputers.MusicApp_Backend.Models.PlaybackSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaybackSessionRepository extends JpaRepository<PlaybackSession, Long> {
    Optional<PlaybackSession> findByUser(User user);
}
