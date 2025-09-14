package com.insurance.notificationService.service;

import com.insurance.notificationService.entity.Notification;
import com.insurance.notificationService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repo;

    public Notification create(Long userId, String type, String message) {
        Notification n = Notification.builder()
                .userId(userId)
                .type(type)
                .message(message)
                .sent(false)
                .createdAt(Instant.now())
                .build();
        return repo.save(n);
    }

    public List<Notification> forUser(Long userId) { return repo.findByUserId(userId); }
    public List<Notification> unsent() { return repo.findBySentFalse(); }

    public Notification markSent(Long id) {
        return repo.findById(id).map(n -> {
            n.setSent(true);
            return repo.save(n);
        }).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public List<Notification> getAllNotifications(){
        return repo.findAll();
    }
}
