package com.example.secure.controller;

import com.example.secure.entity.User;
import com.example.secure.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api",produces = "application/json; charset=UTF-8")
public class RESTController {
    private final UserService serv;
    public RESTController(UserService serv) {
        this.serv = serv;
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return serv.getAllUsers();
    }
    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        serv.removeById(id);
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return serv.findById(id);
    }
    @PatchMapping(value = "/users")
    public ResponseEntity<Object> updateUser(@RequestBody Map<String, Object> updates) {
        Long id = Long.parseLong(updates.get("id").toString());
        String name = updates.get("name").toString();
        String lastName = updates.get("lastName").toString();
        String password = updates.get("password").toString();
        String role = updates.get("role").toString();
        User updatedUser = serv.edit(id, name, lastName, password, role);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/users")
    public ResponseEntity <String> createUser(@RequestBody Map<String, Object> user) {
            String name = user.get("name").toString();
            String lastName = user.get("lastName").toString();
            String password = user.get("password").toString();
            String role = user.get("role").toString();
            User addUser = new User(name,lastName,password);
            serv.saveUser(addUser, role);
            return ResponseEntity.ok("ok");
    }
}