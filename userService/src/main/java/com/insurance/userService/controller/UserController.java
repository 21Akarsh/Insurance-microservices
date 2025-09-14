package com.insurance.userService.controller;

import com.insurance.userService.entity.User;
import com.insurance.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(u -> {
                    u.setPassword(null);
                    return ResponseEntity.ok(u);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        users.forEach(u -> u.setPassword(null)); // remove password before sending
        return ResponseEntity.ok(users);
    }

    @GetMapping("/hello")
    public String sendHello(){
        return "Hello";
    }

}
