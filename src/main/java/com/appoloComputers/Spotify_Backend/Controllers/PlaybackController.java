package com.appoloComputers.Spotify_Backend.Controllers;

import com.appoloComputers.Spotify_Backend.Entities.PlaybackHistory;

import com.appoloComputers.Spotify_Backend.Services.PlaybackHistoryService;

import com.appoloComputers.Spotify_Backend.Services.PlaybackService;
import com.appoloComputers.Spotify_Backend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlaybackController {

    private final PlaybackHistoryService playbackHistoryService;
    private final PlaybackService playbackService;


    @GetMapping("/history")
    public ResponseEntity<List<PlaybackHistory>> getHistory(Principal principal) {
        return ResponseEntity.ok(playbackHistoryService.getUserHistory(principal.getName()));
    }


    @PostMapping("/play/{songId}")
    public ResponseEntity<com.appoloComputers.Spotify_Backend.Models.PlaybackSession> play(@PathVariable Long songId, Principal principal) {
        return ResponseEntity.ok(playbackService.playSong(songId, principal.getName()));
    }


    @PostMapping("/pause")
    public ResponseEntity<com.appoloComputers.Spotify_Backend.Models.PlaybackSession> pause(Principal principal) {
        return ResponseEntity.ok(playbackService.pauseSong(principal.getName()));
    }

    @PostMapping("/resume")
    public ResponseEntity<com.appoloComputers.Spotify_Backend.Models.PlaybackSession> resume(Principal principal) {
        return ResponseEntity.ok(playbackService.resumeSong(principal.getName()));
    }


    @PostMapping("/stop")
    public ResponseEntity<com.appoloComputers.Spotify_Backend.Models.PlaybackSession> stop(Principal principal) {
        return ResponseEntity.ok(playbackService.stopSong(principal.getName()));
    }
    
    @GetMapping("/status")
    public ResponseEntity<com.appoloComputers.Spotify_Backend.Models.PlaybackSession> status(Principal principal) {
        return ResponseEntity.ok(playbackService.getStatus(principal.getName()));
    }
}
