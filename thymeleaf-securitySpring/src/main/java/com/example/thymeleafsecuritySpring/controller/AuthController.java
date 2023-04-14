package com.example.thymeleafsecuritySpring.controller;

import com.example.thymeleafsecuritySpring.request.LoginRequest;
import com.example.thymeleafsecuritySpring.request.RegisterRequest;
import com.example.thymeleafsecuritySpring.security.CustomFilter;
import com.example.thymeleafsecuritySpring.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
@Autowired
private AuthService authService;

    @PostMapping("login-handle")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {

        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);
            // Lưu dữ liệu
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo ra session
            session.setAttribute("MY_SESSION",authentication.getName());

            return ResponseEntity.ok("Login thành công");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam String token) {
        return authService.confirm(token);
//        return null;
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
//        return null;

    }
}
