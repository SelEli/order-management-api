package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.auth.AuthResponse;
import com.ordermanagement.api.dto.auth.LoginRequest;
import com.ordermanagement.api.dto.auth.RegisterRequest;
import com.ordermanagement.api.entity.User;
import com.ordermanagement.api.exception.BadRequestException;
import com.ordermanagement.api.repository.UserRepository;
import com.ordermanagement.api.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthService authService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authService = new AuthService(userRepository, passwordEncoder, jwtService);
    }

    @Test
    void register_shouldCreateUser_whenEmailNotUsed() {
        RegisterRequest req = new RegisterRequest("test@mail.com", "pass");

        when(userRepository.existsByEmail(req.email())).thenReturn(false);
        when(passwordEncoder.encode(req.password())).thenReturn("encoded");
        when(jwtService.generateToken(req.email())).thenReturn("jwt-token");

        AuthResponse response = authService.register(req);

        assertEquals("jwt-token", response.token());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_shouldThrow_whenEmailAlreadyUsed() {
        RegisterRequest req = new RegisterRequest("test@mail.com", "pass");

        when(userRepository.existsByEmail(req.email())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> authService.register(req));
    }

    @Test
    void login_shouldReturnToken_whenCredentialsValid() {
        LoginRequest req = new LoginRequest("test@mail.com", "pass");

        User user = User.builder().email(req.email()).password("encoded").build();

        when(userRepository.findByEmail(req.email())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(req.email())).thenReturn("jwt-token");

        AuthResponse response = authService.login(req);

        assertEquals("jwt-token", response.token());
    }

    @Test
    void login_shouldThrow_whenEmailNotFound() {
        LoginRequest req = new LoginRequest("unknown@mail.com", "pass");

        when(userRepository.findByEmail(req.email())).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> authService.login(req));
    }
}
