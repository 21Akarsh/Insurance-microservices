package com.insurance.claimService.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "claims")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    private Long policyId;
    private Long userId;
    private String status; // PENDING, APPROVED, REJECTED
    private String description;
    private Double amount;
    private Instant createdAt;
}
