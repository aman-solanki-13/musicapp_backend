package com.appoloComputers.Spotify_Backend.Repositories;

import com.appoloComputers.Spotify_Backend.Entities.Playlist;
import com.appoloComputers.Spotify_Backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser(User user);

    Optional<Playlist> findPlaylistByName(String name);
}
