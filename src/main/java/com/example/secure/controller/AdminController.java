package com.example.secure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }
    @GetMapping(value = "/admin")
    public String userList() {
        return "/admin";
    }
}

