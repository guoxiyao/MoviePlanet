package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    // 查找用户的所有好友关系
    List<Friendship> findByUser1_UserIdOrUser2_UserId(Long userId1, Long userId2);

    // 根据两个用户ID查找特定好友关系
    Friendship findByUser1_UserIdAndUser2_UserId(Long userId1, Long userId2);

    // 根据状态查找用户的好友请求
    List<Friendship> findByUser1_UserIdAndStatusOrUser2_UserIdAndStatus(Long userId1, Friendship.Status status1, Long userId2, Friendship.Status status2);
}
