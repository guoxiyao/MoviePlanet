package com.example.movieplanet.services;

import com.example.movieplanet.models.Settings;
import com.example.movieplanet.repositories.SettingsRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    // 根据用户ID获取设置
    public Optional<Settings> getSettingsByUserId(Long userId) {
        return Optional.ofNullable(settingsRepository.findByUser_UserId(userId));
    }

    // 保存或更新用户设置
    public Settings saveOrUpdateSettings(Settings settings) {
        return settingsRepository.save(settings);
    }

    // 删除设置
    public void deleteSettings(Long settingId) {
        if (!settingsRepository.existsById(settingId)) {
            throw new ResourceNotFoundException("Settings not found with id: " + settingId);
        }
        settingsRepository.deleteById(settingId);
    }

}
