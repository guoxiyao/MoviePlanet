package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Settings;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    // 根据用户ID获取设置
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<Settings>> getSettingsByUserId(@PathVariable Long userId) {
        Optional<Settings> settings = settingsService.getSettingsByUserId(userId);
        return settings.map(s -> ResponseEntity.ok(ApiResponse.success(s)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error(404, "Settings not found")));
    }

    // 保存或更新设置
    @PostMapping
    public ResponseEntity<ApiResponse<Settings>> saveOrUpdateSettings(@RequestBody Settings settings) {
        Settings savedSettings = settingsService.saveOrUpdateSettings(settings);
        return ResponseEntity.ok(ApiResponse.success(savedSettings, "Settings saved successfully"));
    }

    // 删除设置
    @DeleteMapping("/{settingId}")
    public ResponseEntity<ApiResponse<Void>> deleteSettings(@PathVariable Long settingId) {
        settingsService.deleteSettings(settingId);
        return ResponseEntity.ok(ApiResponse.success(null, "Settings deleted successfully"));
    }
}

