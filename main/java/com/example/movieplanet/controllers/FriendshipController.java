package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Friendship;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    // 发送好友请求
    @PostMapping("/request")
    public ResponseEntity<ApiResponse<Friendship>> sendFriendRequest(@RequestBody Friendship friendship) {
        Friendship createdFriendship = friendshipService.sendFriendRequest(friendship);
        return ResponseEntity.ok(ApiResponse.success(createdFriendship, "Friend request sent successfully"));
    }

    // 接受好友请求
    @PutMapping("/{friendId}/accept")
    public ResponseEntity<ApiResponse<Friendship>> acceptFriendRequest(@PathVariable Long friendId) {
        Friendship acceptedFriendship = friendshipService.acceptFriendRequest(friendId);
        return ResponseEntity.ok(ApiResponse.success(acceptedFriendship, "Friend request accepted successfully"));
    }

    // 阻止好友
    @PutMapping("/{friendId}/block")
    public ResponseEntity<ApiResponse<Friendship>> blockFriend(@PathVariable Long friendId) {
        Friendship blockedFriendship = friendshipService.blockFriend(friendId);
        return ResponseEntity.ok(ApiResponse.success(blockedFriendship, "Friend blocked successfully"));
    }

    // 获取用户的好友列表
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Friendship>>> getFriends(@PathVariable Long userId) {
        List<Friendship> friends = friendshipService.getFriends(userId);
        return ResponseEntity.ok(ApiResponse.success(friends));
    }

    // 获取用户的待处理好友请求
    @GetMapping("/user/{userId}/pending")
    public ResponseEntity<ApiResponse<List<Friendship>>> getPendingFriendRequests(@PathVariable Long userId) {
        List<Friendship> pendingRequests = friendshipService.getPendingFriendRequests(userId);
        return ResponseEntity.ok(ApiResponse.success(pendingRequests));
    }
}
