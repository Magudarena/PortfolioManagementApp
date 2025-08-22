package com.CASH.CASH.controller;

import com.CASH.CASH.model.LoginRequest;
import com.CASH.CASH.model.LoginResponse;
import com.CASH.CASH.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
