package com.insurance.policyService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String policyNumber;

    private String policyType;
    private Double premiumAmount;
    private Long customerId; // reference to user id
}
