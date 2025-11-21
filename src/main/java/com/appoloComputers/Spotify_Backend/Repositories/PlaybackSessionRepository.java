package com.appoloComputers.Spotify_Backend.Repositories;


import com.appoloComputers.Spotify_Backend.Entities.User;
import com.appoloComputers.Spotify_Backend.Models.PlaybackSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaybackSessionRepository extends JpaRepository<PlaybackSession, Long> {
    Optional<PlaybackSession> findByUser(User user);
}
