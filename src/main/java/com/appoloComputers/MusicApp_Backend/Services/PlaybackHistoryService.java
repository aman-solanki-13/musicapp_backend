package com.appoloComputers.MusicApp_Backend.Services;

import com.appoloComputers.MusicApp_Backend.Entities.PlaybackHistory;
import com.appoloComputers.MusicApp_Backend.Entities.User;
import com.appoloComputers.MusicApp_Backend.Repositories.PlaybackHistoryRepository;
import com.appoloComputers.MusicApp_Backend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaybackHistoryService {

    private final PlaybackHistoryRepository playbackHistoryRepository;
    private final UserRepository userRepository;


    public List<PlaybackHistory> getUserHistory(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return playbackHistoryRepository.findByUserOrderByPlayedAtDesc(user);
    }
}
