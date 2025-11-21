package com.appoloComputers.Spotify_Backend.Controllers;

import com.appoloComputers.Spotify_Backend.DTOs.UserDashboardDTO;
import com.appoloComputers.Spotify_Backend.Entities.Song;
import com.appoloComputers.Spotify_Backend.Entities.User;
import com.appoloComputers.Spotify_Backend.Repositories.UserRepository;
import com.appoloComputers.Spotify_Backend.Services.AdminService;
import com.appoloComputers.Spotify_Backend.Services.AuthService;
import com.appoloComputers.Spotify_Backend.Services.SongService;
import com.appoloComputers.Spotify_Backend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SongService songService;
    private final AdminService adminService;
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/song")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        return ResponseEntity.ok(songService.addSong(song));
    }

    @GetMapping("/users-dump-data")
    public ResponseEntity<List<UserDashboardDTO>> getAllUsersData() {
        return ResponseEntity.ok(adminService.getAllUsersWithDetails());
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PutMapping("/updateUserPassword")
    public ResponseEntity<String> updatePassword(@RequestBody User user) {
        userService.updatePassword(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("Password Updated");
    }
}
