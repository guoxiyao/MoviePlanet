package com.example.movieplanet.controllers;

import com.example.movieplanet.models.User;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册新用户
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody User user) {
        if (userService.findUserByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(409).body(ApiResponse.error(409, "Username already exists"));
        }
        User registeredUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok(ApiResponse.success(registeredUser, "User registered successfully"));
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.loginUser(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(user.get(), "Login successful"));
        }
        return ResponseEntity.status(401).body(ApiResponse.error(401, "Invalid username or password"));
    }

    // 根据用户名查找用户
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<Optional<User>>> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(user));
        }
        return ResponseEntity.status(404).body(ApiResponse.error(404, "User not found"));
    }

    // 根据用户ID获取用户信息
    @GetMapping("/id/{userId}")
    public ResponseEntity<ApiResponse<Optional<User>>> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.findUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(ApiResponse.success(user));
        }
        return ResponseEntity.status(404).body(ApiResponse.error(404, "User not found"));
    }

    // 更新用户的最后登录时间
    @PutMapping("/updateLogin/{userId}")
    public ResponseEntity<ApiResponse<Void>> updateLastLogin(@PathVariable Long userId) {
        userService.updateUserLoginTime(userId);
        return ResponseEntity.ok(ApiResponse.success(null, "Login time updated successfully"));
    }

    // 根据用户ID删除用户
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponse.success(null, "User deleted successfully"));
    }
}
