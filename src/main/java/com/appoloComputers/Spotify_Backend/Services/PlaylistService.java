package com.appoloComputers.Spotify_Backend.Services;

import com.appoloComputers.Spotify_Backend.Entities.Playlist;
import com.appoloComputers.Spotify_Backend.Entities.Song;
import com.appoloComputers.Spotify_Backend.Entities.User;
import com.appoloComputers.Spotify_Backend.Repositories.PlaylistRepository;
import com.appoloComputers.Spotify_Backend.Repositories.SongRepository;
import com.appoloComputers.Spotify_Backend.Repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    // 1. Create Playlist
    public Playlist createPlaylist(String name, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Optional<Playlist> playlistByName = playlistRepository.findPlaylistByName(name);
        if (playlistByName.isPresent()) {
            throw new RuntimeException("Playlist with name " + name + " already exists");
        }
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        playlistRepository.delete(playlist);
    }

    @Transactional
    public Playlist addSongToPlaylist(Long playlistId, Long songId, String username) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getUsername().equals(username)) {
            throw new UsernameNotFoundException("Username not found");
        }

        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));


        playlist.getSongs().add(song);
        return playlistRepository.save(playlist);
    }

    public void deleteSongFromPlaylist(Long playlistId, Long songId, String username) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUser().getUsername().equals(username)) {
            throw new UsernameNotFoundException("Username not found");
        }

        playlist.getSongs().remove(songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found")));

        playlistRepository.save(playlist);
    }


    public List<Playlist> getMyPlaylists(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return playlistRepository.findByUser(user);
    }
}
