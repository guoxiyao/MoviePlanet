package com.example.movieplanet.services;

import com.example.movieplanet.models.User;
import com.example.movieplanet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 注册新用户
    public User registerUser(String username, String rawPassword, String email) {
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        User newUser = new User(username, encodedPassword, email); // 使用带参构造器
        return userRepository.save(newUser);
    }



    // 根据用户名查找用户
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    // 根据用户ID查找用户
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // 更新用户最后登录时间
    public void updateUserLoginTime(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
        });
    }

    // 登录用户
    public Optional<User> loginUser(String username, String rawPassword) {
        Optional<User> user = findUserByUsername(username);
        if (user.isPresent() && bCryptPasswordEncoder.matches(rawPassword, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    // 删除用户
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}


