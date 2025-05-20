package com.trainingprogram.springsecurity_api.controller;

import com.trainingprogram.springsecurity_api.dto.request.LoginRequest;
import com.trainingprogram.springsecurity_api.dto.request.SignupRequest;
import com.trainingprogram.springsecurity_api.model.User;
import com.trainingprogram.springsecurity_api.repository.UserRepository;
import com.trainingprogram.springsecurity_api.security.JwtUtil;
import com.trainingprogram.springsecurity_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api") // add prefix
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/home")
    public String home() {
        return "This is home page";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "This is dashboard page";
    }

    @GetMapping("/manage")
    public String manage() {
        return "This is manage page";
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return authService.getAllUsers(); // Can return DTO instead if preferred
    }
}