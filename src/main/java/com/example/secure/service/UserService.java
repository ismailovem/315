package com.example.secure.service;

import com.example.secure.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    void saveUser(User user, String[] roles);
    User findById(Long id);
    void removeById(Long id);
    //User edit(Long id,String name,String lastName,String password, String role);
    void update(User user, Long id,String[] roles);
    List<User> getAllUsers();
    User getPrincipal();
}

