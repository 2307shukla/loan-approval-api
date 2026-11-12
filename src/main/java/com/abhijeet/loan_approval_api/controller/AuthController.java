package com.abhijeet.loan_approval_api.controller;

import com.abhijeet.loan_approval_api.entity.User;
import com.abhijeet.loan_approval_api.repository.UserRepository;
import com.abhijeet.loan_approval_api.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "APIs for Authentication")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Register a new user",
               description = "Register with username and password")
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> request) {
        User user = new User();
        user.setUsername(request.get("username"));
        user.setPassword(passwordEncoder.encode(request.get("password")));
        user.setRole("USER");
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        return response;
    }

    @Operation(summary = "Login",
               description = "Login with username and password - returns JWT token")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.get("username"),
                request.get("password")
            )
        );

        String token = jwtUtil.generateToken(request.get("username"));

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Login successful!");
        return response;
    }
}