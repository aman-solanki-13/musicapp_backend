package com.appoloComputers.MusicApp_Backend.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class UserDashboardDTO {
    private Long userId;
    private String username;
    private String role;
    private List<PlaylistDTO> playlists;

    @Data
    public static class PlaylistDTO {
        private Long id;
        private String name;
        private int songCount;
        private List<SongDTO> songs;
    }

    @Data
    public static class SongDTO {
        private String title;
        private String artist;
        private String genre;
        private int duration;
    }
}
