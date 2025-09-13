package com.insurance.userService.controller;

import com.insurance.userService.dto.AuthRequest;
import com.insurance.userService.dto.AuthResponse;
import com.insurance.userService.entity.User;
import com.insurance.userService.repository.UserRepository;
import com.insurance.userService.security.JwtUtil;
import com.insurance.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User created = userService.register(user);
        created.setPassword(null);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .map(u -> {
                    if (passwordEncoder.matches(request.getPassword(), u.getPassword())) {
                        String token = jwtUtil.generateToken(u.getUsername());
                        return ResponseEntity.ok(new AuthResponse(token));
                    } else {
                        return ResponseEntity.status(401).body("Invalid credentials");
                    }
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
