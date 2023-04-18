package com.example.secure.controller;

import com.example.secure.entity.User;
import com.example.secure.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api",produces = "application/json; charset=UTF-8")
public class UserRESTController {
    private final UserService serv;
    public UserRESTController(UserService serv) {
        this.serv = serv;
    }
    @GetMapping("/user")
    public User getUserForUserPage() {
        return serv.getPrincipal();
    }
}
