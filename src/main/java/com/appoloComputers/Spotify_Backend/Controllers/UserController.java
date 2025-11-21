package com.appoloComputers.Spotify_Backend.Controllers;

import com.appoloComputers.Spotify_Backend.Entities.User;
import com.appoloComputers.Spotify_Backend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/user") // Mapped to /api/user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getMyProfile(Principal principal) {
        return ResponseEntity.ok(userService.getMyProfile(principal.getName()));
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> payload, Principal principal) {
        String newPassword = payload.get("password");
        userService.updatePassword(principal.getName(), newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
    
    @DeleteMapping("/account")
    public ResponseEntity<String> deleteAccount(Principal principal) {
        userService.deleteMyAccount(principal.getName());
        return ResponseEntity.ok("Account deleted successfully");
    }
}
