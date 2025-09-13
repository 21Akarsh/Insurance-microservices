package com.insurance.policyService.service;

import com.insurance.policyService.entity.Policy;
import com.insurance.policyService.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyRepository repo;

    public Policy create(Policy p) { return repo.save(p); }
    public Optional<Policy> getById(Long id) { return repo.findById(id); }
    public List<Policy> getAll() { return repo.findAll(); }
    public Policy update(Long id, Policy updated) {
        return repo.findById(id).map(p -> {
            p.setPolicyNumber(updated.getPolicyNumber());
            p.setPolicyType(updated.getPolicyType());
            p.setPremiumAmount(updated.getPremiumAmount());
            p.setCustomerId(updated.getCustomerId());
            return repo.save(p);
        }).orElseThrow(() -> new RuntimeException("Policy not found"));
    }
    public void delete(Long id) { repo.deleteById(id); }
}
