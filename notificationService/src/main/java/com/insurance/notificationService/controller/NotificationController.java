//package com.insurance.notificationService.controller;
//
//public class NotificationController {
//}
package com.insurance.notificationService.controller;

import com.insurance.notificationService.entity.Notification;
import com.insurance.notificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @PostMapping("/send")
    public ResponseEntity<Notification> send(@RequestParam Long userId,
                                             @RequestParam String type,
                                             @RequestParam String message) {
        return ResponseEntity.ok(service.create(userId, type, message));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> byUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.forUser(userId));
    }

    @GetMapping("/unsent")
    public ResponseEntity<List<Notification>> unsent() {
        return ResponseEntity.ok(service.unsent());
    }

    @PutMapping("/{id}/sent")
    public ResponseEntity<Notification> markSent(@PathVariable Long id) {
        return ResponseEntity.ok(service.markSent(id));
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

}
