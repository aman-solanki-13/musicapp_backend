package com.appoloComputers.MusicApp_Backend.Controllers;

import com.appoloComputers.MusicApp_Backend.Entities.User;
import com.appoloComputers.MusicApp_Backend.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(java.security.Principal principal) {
        return ResponseEntity.ok("You are logged in as: " + principal.getName());
    }

}
