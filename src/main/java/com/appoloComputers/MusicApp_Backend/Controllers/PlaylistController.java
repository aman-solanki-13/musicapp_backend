package com.appoloComputers.MusicApp_Backend.Controllers;

import com.appoloComputers.MusicApp_Backend.Entities.Playlist;
import com.appoloComputers.MusicApp_Backend.Services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestParam String name, Principal principal) {
        return ResponseEntity.ok(playlistService.createPlaylist(name, principal.getName()));
    }

    @DeleteMapping("/deletePlaylist/{playlistId}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.ok("Playlist deleted");
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<String> removeSong(
            @PathVariable Long playlistId,
            @PathVariable Long songId,
            Principal principal
    ) {
        playlistService.deleteSongFromPlaylist(playlistId, songId, principal.getName());
        return ResponseEntity.ok("Song deleted successfully");
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId, Principal principal) {
        return ResponseEntity.ok(playlistService.addSongToPlaylist(playlistId, songId, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists(Principal principal) {
        return ResponseEntity.ok(playlistService.getMyPlaylists(principal.getName()));
    }
}
