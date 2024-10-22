package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Notification;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // 创建通知
    @PostMapping
    public ResponseEntity<ApiResponse<Notification>> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(ApiResponse.success(createdNotification, "Notification created successfully"));
    }

    // 获取用户的所有通知
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Notification>>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(notifications));
    }

    // 获取用户的未读通知
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<ApiResponse<List<Notification>>> getUnreadNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> unreadNotifications = notificationService.getUnreadNotificationsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(unreadNotifications));
    }

    // 标记通知为已读
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<Notification>> markAsRead(@PathVariable Long notificationId) {
        Notification updatedNotification = notificationService.markAsRead(notificationId);
        if (updatedNotification == null) {
            return ResponseEntity.status(404).body(ApiResponse.error(404, "Notification not found"));
        }
        return ResponseEntity.ok(ApiResponse.success(updatedNotification, "Notification marked as read"));
    }

    // 删除通知
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ApiResponse<Void>> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok(ApiResponse.success(null, "Notification deleted successfully"));
    }
}
