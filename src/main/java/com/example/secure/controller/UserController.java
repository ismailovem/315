package com.example.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }
    @GetMapping(value = "")
    public String userPage() {
        return "user";
    }
}

