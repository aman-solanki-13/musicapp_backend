package com.appoloComputers.Spotify_Backend.Repositories;

import com.appoloComputers.Spotify_Backend.Entities.PlaybackHistory;
import com.appoloComputers.Spotify_Backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaybackHistoryRepository extends JpaRepository<PlaybackHistory, Long> {
    List<PlaybackHistory> findByUserOrderByPlayedAtDesc(User user);
}