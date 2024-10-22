package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // 根据用户ID查询通知
    List<Notification> findByUser_UserId(Long userId);

    // 根据用户ID和状态查询未读通知
    List<Notification> findByUser_UserIdAndStatus(Long userId, Notification.Status status);
}
