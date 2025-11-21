package com.appoloComputers.Spotify_Backend.Services;

import com.appoloComputers.Spotify_Backend.Entities.PlaybackHistory;
import com.appoloComputers.Spotify_Backend.Entities.PlaybackStatus;
import com.appoloComputers.Spotify_Backend.Entities.Song;
import com.appoloComputers.Spotify_Backend.Entities.User;
import com.appoloComputers.Spotify_Backend.Models.PlaybackSession;
import com.appoloComputers.Spotify_Backend.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlaybackService {

    private final PlaybackSessionRepository sessionRepository;
    private final PlaybackHistoryRepository historyRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    private PlaybackSession getSession(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return sessionRepository.findByUser(user)
                .orElseGet(() -> {
                    PlaybackSession newSession = new PlaybackSession();
                    newSession.setUser(user);
                    newSession.setStatus(PlaybackStatus.STOPPED);
                    newSession.setProgressSeconds(0);
                    return sessionRepository.save(newSession);
                });
    }

    public PlaybackSession playSong(Long songId, String username) {
        PlaybackSession session = getSession(username);
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        session.setCurrentSong(song);
        session.setStatus(PlaybackStatus.PLAYING);
        session.setProgressSeconds(0); // Start from beginning

        // Log to History
        PlaybackHistory history = new PlaybackHistory();
        history.setUser(session.getUser());
        history.setSong(song);
        history.setPlayedAt(LocalDateTime.now());
        historyRepository.save(history);

        return sessionRepository.save(session);
    }

    public PlaybackSession pauseSong(String username) {
        PlaybackSession session = getSession(username);
        if (session.getStatus() == PlaybackStatus.PLAYING) {
            session.setStatus(PlaybackStatus.PAUSED);
            sessionRepository.save(session);
        }
        return session;
    }


    public PlaybackSession resumeSong(String username) {
        PlaybackSession session = getSession(username);
        if (session.getStatus() == PlaybackStatus.PAUSED && session.getCurrentSong() != null) {
            session.setStatus(PlaybackStatus.PLAYING);
            sessionRepository.save(session);
        }
        return session;
    }


    public PlaybackSession stopSong(String username) {
        PlaybackSession session = getSession(username);
        session.setStatus(PlaybackStatus.STOPPED);
        session.setProgressSeconds(0);
        sessionRepository.save(session);
        return session;
    }


    public PlaybackSession getStatus(String username) {
        return getSession(username);
    }
}
