package com.mobileprovider.midterm4458.controller;

import com.mobileprovider.midterm4458.config.JwtUtil;
import com.mobileprovider.midterm4458.model.AuthRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "JWT token almak için giriş işlemleri")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Login işlemi", description = "Kullanıcı adı ve şifre ile giriş yapar ve JWT token döner.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
