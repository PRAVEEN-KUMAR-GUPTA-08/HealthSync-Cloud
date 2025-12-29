package com.healthsync.healthsync.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String registerUser() {
        return "User registration endpoint (to be implemented)";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "User login endpoint (to be implemented)";
    }
}
