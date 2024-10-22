package com.example.movieplanet.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "friends", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id1", "user_id2"})
})
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @ManyToOne
    @JoinColumn(name = "user_id1", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id2", nullable = false)
    private User user2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public enum Status {
        PENDING, ACCEPTED, BLOCKED
    }

    // 构造函数
    public Friendship() {}

    public Friendship(User user1, User user2, Status status) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    // Getters 和 Setters
    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
