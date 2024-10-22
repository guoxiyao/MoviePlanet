package com.example.movieplanet.services;

import com.example.movieplanet.models.Role;
import com.example.movieplanet.models.User;
import com.example.movieplanet.repositories.RoleRepository;
import com.example.movieplanet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // 保存角色
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // 分配角色给用户
    public void assignRoleToUser(Long userId, String roleName) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findByName(roleName);

        if (user.isPresent() && role.isPresent()) {
            user.get().getRoles().add(role.get());
            userRepository.save(user.get());
        } else {
            throw new RuntimeException("User or Role not found");
        }
    }
}
