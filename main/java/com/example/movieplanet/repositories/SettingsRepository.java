package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    // 根据用户ID查找用户的设置
    Settings findByUser_UserId(Long userId);
}
