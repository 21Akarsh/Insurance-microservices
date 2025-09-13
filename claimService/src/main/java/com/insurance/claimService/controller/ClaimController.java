package com.insurance.claimService.controller;

import com.insurance.claimService.entity.Claim;
import com.insurance.claimService.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {
    private final ClaimService service;

    @PostMapping
    public ResponseEntity<Claim> submit(@RequestBody Claim claim) {
        return ResponseEntity.ok(service.submit(claim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> get(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Claim>> byUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<Claim>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Claim> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}
