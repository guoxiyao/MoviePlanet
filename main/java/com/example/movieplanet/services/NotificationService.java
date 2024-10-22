package com.example.movieplanet.services;

import com.example.movieplanet.models.Notification;
import com.example.movieplanet.repositories.NotificationRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // 创建通知
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // 根据用户ID获取通知
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUser_UserId(userId);
    }

    // 获取用户的未读通知
    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findByUser_UserIdAndStatus(userId, Notification.Status.UNREAD);
    }

    // 标记通知为已读
    public Notification markAsRead(Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isEmpty()) {
            throw new ResourceNotFoundException("Notification not found with id: " + notificationId);
        }
        Notification notification = optionalNotification.get();
        notification.setStatus(Notification.Status.READ);
        notification.setReadAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    // 删除通知
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new ResourceNotFoundException("Notification not found with id: " + notificationId);
        }
        notificationRepository.deleteById(notificationId);
    }
}
