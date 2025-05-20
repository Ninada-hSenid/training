package com.trainingprogram.springsecurity_api.service;

import com.trainingprogram.springsecurity_api.dto.mapper.UserMapper;
import com.trainingprogram.springsecurity_api.dto.request.LoginRequest;
import com.trainingprogram.springsecurity_api.dto.request.SignupRequest;
import com.trainingprogram.springsecurity_api.dto.response.ErrorResponse;
import com.trainingprogram.springsecurity_api.dto.response.LoginResponse;
import com.trainingprogram.springsecurity_api.model.User;
import com.trainingprogram.springsecurity_api.repository.UserRepository;
import com.trainingprogram.springsecurity_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public String register(SignupRequest request) {
        String role = (request.getRole() != null && !request.getRole().isBlank()) ? request.getRole() : "USER";
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = UserMapper.toUser(request, encodedPassword, role);
        userRepo.save(user);
        return "User registered with role: " + role;
    }

    public ResponseEntity<?> login(LoginRequest request) {
        // Keep existing user lookup logic
        Optional<User> userOpt = userRepo.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(
                            "Invalid credentials",
                            "No account found with this username"
                    ));
        }

        try {
            // Keep existing authentication flow
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(
                    token,
                    "Login successful"
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(
                            "Invalid credentials",
                            "Incorrect password for this username"
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            "Login error",
                            "An unexpected error occurred: " + e.getMessage()
                    ));
        }
    }



    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }
}