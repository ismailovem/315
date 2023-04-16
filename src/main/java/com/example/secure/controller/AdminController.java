package com.example.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String adminPage() {
        return "index";
    }
    @GetMapping(value = "")
    public String userList() {
        return "/admin";
    }
}