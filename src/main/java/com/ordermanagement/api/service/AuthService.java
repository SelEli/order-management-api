package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.auth.AuthResponse;
import com.ordermanagement.api.dto.auth.LoginRequest;
import com.ordermanagement.api.dto.auth.RegisterRequest;
import com.ordermanagement.api.entity.User;
import com.ordermanagement.api.exception.BadRequestException;
import com.ordermanagement.api.repository.UserRepository;
import com.ordermanagement.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("Email already in use");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        // ici normalement tu vérifies le mot de passe avec passwordEncoder.matches(...)
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
