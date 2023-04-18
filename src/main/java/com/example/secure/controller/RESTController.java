package com.example.secure.controller;

import com.example.secure.entity.User;
import com.example.secure.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/admin")
    public User getUserForAdminPage() {
        return serv.getPrincipal();
    }
    @GetMapping("/user")
    public User getUserForUserPage() {
        return serv.getPrincipal();
    }
    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        serv.removeById(id);
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return serv.findById(id);
    }
    @PatchMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam(name = "id") Long id, @RequestParam(name = "roles") String roles) {
       serv.update(user,id,roles);
       return ResponseEntity.ok(user);
    }
    @PostMapping("/users")
    public ResponseEntity <String> createUser(@RequestBody User user, @RequestParam(name = "role") String role) {
        serv.saveUser(user,role);
        return ResponseEntity.ok("ok");
    }
}