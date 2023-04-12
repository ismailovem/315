package com.example.secure.service;


import com.example.secure.entity.Role;
import com.example.secure.entity.User;
import com.example.secure.repository.RoleRepository;
import com.example.secure.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found "+ username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()).authorities(user.getRoles()).build();
    }
    @Transactional
    @Override
    public void saveUser(User user,String role) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        List<Role> list = Stream.of(roleRepository.findByRole(role.trim())).toList();
        user.setRoles(list);
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void update(User user, Long id,String role) {
        User forUpdateUser = findById(id);
        forUpdateUser.setUsername(user.getUsername());
        forUpdateUser.setLastName(user.getLastName());
        forUpdateUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        forUpdateUser.setEnabled(true);
        List<Role> list = Stream.of(roleRepository.findByRole(role.trim())).toList();
        user.setRoles(list);
        forUpdateUser.setRoles(user.getRoles());
    }
    @Transactional
    @Override
    public User edit(Long id, String name,String lastName, String password, String role) {
        User forUpdateUser = findById(id);
        forUpdateUser.setUsername(name);
        forUpdateUser.setLastName(lastName);
        forUpdateUser.setPassword(new BCryptPasswordEncoder().encode(password));
        forUpdateUser.setEnabled(true);
        List<Role> list = Stream.of(roleRepository.findByRole(role.trim())).toList();
        forUpdateUser.setRoles(list);
        return forUpdateUser;
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}