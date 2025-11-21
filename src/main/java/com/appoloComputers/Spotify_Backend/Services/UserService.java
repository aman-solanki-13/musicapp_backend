package com.appoloComputers.Spotify_Backend.Services;

import com.appoloComputers.Spotify_Backend.Entities.User;
import com.appoloComputers.Spotify_Backend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getMyProfile(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public void updatePassword(String username, String newPassword) {
        User user = getMyProfile(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    public void deleteMyAccount(String username) {
        User user = getMyProfile(username);
        userRepository.delete(user);
    }
}


