package com.ordermanagement.api.controller;

import com.ordermanagement.api.dto.auth.AuthResponse;
import com.ordermanagement.api.dto.auth.LoginRequest;
import com.ordermanagement.api.dto.auth.RegisterRequest;
import com.ordermanagement.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
