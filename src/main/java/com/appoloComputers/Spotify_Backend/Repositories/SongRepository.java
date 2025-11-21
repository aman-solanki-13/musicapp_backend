package com.appoloComputers.Spotify_Backend.Repositories;

import com.appoloComputers.Spotify_Backend.Entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCaseOrGenreContainingIgnoreCase(
            String title, String artist, String genre
    );
}
