package com.appoloComputers.MusicApp_Backend.Models;

import com.appoloComputers.MusicApp_Backend.Entities.PlaybackStatus;
import com.appoloComputers.MusicApp_Backend.Entities.Song;
import com.appoloComputers.MusicApp_Backend.Entities.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "playback_sessions")
public class PlaybackSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "current_song_id")
    private Song currentSong;

    @Enumerated(EnumType.STRING)
    private PlaybackStatus status;

    // Simulated progress in seconds (e.g., paused at 1:30)
    private int progressSeconds;
}
