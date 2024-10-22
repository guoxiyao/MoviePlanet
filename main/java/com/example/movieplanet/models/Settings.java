package com.example.movieplanet.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String theme;

    @Column(nullable = false, length = 10)
    private String language;

    @Column(nullable = false)
    private Boolean notification;

    @Lob
    private String privacySettings;  // 使用文本字段存储 JSON 格式的隐私设置

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    // 构造函数
    public Settings() {
    }

    public Settings(User user, String theme, String language, Boolean notification, String privacySettings) {
        this.user = user;
        this.theme = theme;
        this.language = language;
        this.notification = notification;
        this.privacySettings = privacySettings;
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters 和 Setters
    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getPrivacySettings() {
        return privacySettings;
    }

    public void setPrivacySettings(String privacySettings) {
        this.privacySettings = privacySettings;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
