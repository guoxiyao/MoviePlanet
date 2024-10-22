package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Role;
import com.example.movieplanet.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 创建角色
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    // 分配角色给用户
    @PutMapping("/assign")
    public void assignRoleToUser(@RequestParam Long userId, @RequestParam String roleName) {
        roleService.assignRoleToUser(userId, roleName);
    }
}
