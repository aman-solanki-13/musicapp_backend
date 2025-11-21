package com.appoloComputers.MusicApp_Backend.Services;

import com.appoloComputers.MusicApp_Backend.DTOs.UserDashboardDTO;
import com.appoloComputers.MusicApp_Backend.Entities.Playlist;
import com.appoloComputers.MusicApp_Backend.Entities.User;
import com.appoloComputers.MusicApp_Backend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDashboardDTO> getAllUsersWithDetails() {
        List<User> users = userRepository.findAll();

        // Convert Entity -> DTO
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDashboardDTO convertToDTO(User user) {
        UserDashboardDTO dto = new UserDashboardDTO();
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());

        // Map Playlists
        if (user.getPlaylists() != null) {
            List<UserDashboardDTO.PlaylistDTO> playlistDTOs = user.getPlaylists().stream()
                    .map(this::convertPlaylistToDTO)
                    .collect(Collectors.toList());
            dto.setPlaylists(playlistDTOs);
        }

        return dto;
    }

    private UserDashboardDTO.PlaylistDTO convertPlaylistToDTO(Playlist playlist) {
        UserDashboardDTO.PlaylistDTO dto = new UserDashboardDTO.PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setName(playlist.getName());
        dto.setSongCount(playlist.getSongs().size());

        // Map Songs
        List<UserDashboardDTO.SongDTO> songDTOs = playlist.getSongs().stream()
                .map(song -> {
                    UserDashboardDTO.SongDTO songDTO = new UserDashboardDTO.SongDTO();
                    songDTO.setTitle(song.getTitle());
                    songDTO.setArtist(song.getArtist());
                    songDTO.setGenre(song.getGenre());
                    songDTO.setDuration(song.getDuration());
                    return songDTO;
                }).collect(Collectors.toList());

        dto.setSongs(songDTOs);
        return dto;
    }
}
