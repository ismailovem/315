package com.example.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {
    @GetMapping(value = "/user")
    public String userPage() {
        return "/user";
    }
}
