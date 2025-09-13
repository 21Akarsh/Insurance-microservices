package com.insurance.claimService.service;

import com.insurance.claimService.entity.Claim;
import com.insurance.claimService.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimRepository repo;

    public Claim submit(Claim claim) {
        claim.setStatus("PENDING");
        claim.setCreatedAt(Instant.now());
        return repo.save(claim);
    }

    public Optional<Claim> getById(Long id) { return repo.findById(id); }
    public List<Claim> getByUser(Long userId) { return repo.findByUserId(userId); }
    public List<Claim> getAll() { return repo.findAll(); }

    public Claim updateStatus(Long id, String status) {
        return repo.findById(id).map(c -> {
            c.setStatus(status);
            return repo.save(c);
        }).orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}
