package com.appoloComputers.Spotify_Backend.Controllers;

import com.appoloComputers.Spotify_Backend.Entities.Song;
import com.appoloComputers.Spotify_Backend.Services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Song>> searchSongByTypes(@RequestParam String query) {
        return ResponseEntity.ok(songService.searchSongs(query, query, query));
    }

}
