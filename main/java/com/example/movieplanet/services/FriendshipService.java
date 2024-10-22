package com.example.movieplanet.services;

import com.example.movieplanet.models.Friendship;
import com.example.movieplanet.repositories.FriendshipRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    // 发送好友请求
    public Friendship sendFriendRequest(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    // 接受好友请求
    public Friendship acceptFriendRequest(Long friendId) {
        Friendship friendship = friendshipRepository.findById(friendId)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found with id: " + friendId));
        friendship.setStatus(Friendship.Status.ACCEPTED);
        return friendshipRepository.save(friendship);
    }

    // 阻止好友
    public Friendship blockFriend(Long friendId) {
        Friendship friendship = friendshipRepository.findById(friendId)
                .orElseThrow(() -> new ResourceNotFoundException("Friendship not found with id: " + friendId));
        friendship.setStatus(Friendship.Status.BLOCKED);
        return friendshipRepository.save(friendship);
    }

    // 获取用户的好友列表
    public List<Friendship> getFriends(Long userId) {
        return friendshipRepository.findByUser1_UserIdOrUser2_UserId(userId, userId);
    }

    // 获取待处理的好友请求
    public List<Friendship> getPendingFriendRequests(Long userId) {
        return friendshipRepository.findByUser1_UserIdAndStatusOrUser2_UserIdAndStatus(userId, Friendship.Status.PENDING, userId, Friendship.Status.PENDING);
    }
}
