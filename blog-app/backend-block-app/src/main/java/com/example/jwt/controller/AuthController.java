package com.example.jwt.controller;

import com.example.jwt.repository.AuthResponse;
import com.example.jwt.entity.User;
import com.example.jwt.mapper.UserMapper;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.request.LoginRequest;
import com.example.jwt.security.CustomUserDetailsService;
import com.example.jwt.security.JwtUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public")
public class AuthController {
@Autowired
private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu dữ liệu vào context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // TODO: Tạo ra token -> trả về thông tin sau khi đăng nhập
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
            //tạo ra token
            String jwtToken = jwtUtils.generateToken(userDetails);
            // Tìm kiếm user
            User user = userRepository.findByEmail(authentication.getName()).orElse( null);
            return ResponseEntity.ok(new AuthResponse(
                    UserMapper.toUserDto(user),
                    jwtToken,
                    true
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
