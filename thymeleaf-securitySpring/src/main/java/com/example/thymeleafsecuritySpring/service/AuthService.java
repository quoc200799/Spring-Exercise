package com.example.thymeleafsecuritySpring.service;

import com.example.thymeleafsecuritySpring.entity.TokenConfirm;
import com.example.thymeleafsecuritySpring.entity.User;
import com.example.thymeleafsecuritySpring.repository.RoleRepository;
import com.example.thymeleafsecuritySpring.repository.TokenConfirmRepository;
import com.example.thymeleafsecuritySpring.repository.UserRepository;
import com.example.thymeleafsecuritySpring.request.RegisterRequest;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public String confirm(String token) {
//        List<TokenConfirm> tokenConfirms = tokenConfirmRepository.findAll();
        User user = userRepository.findByTokenConfirms_Token(token).orElseThrow(() -> {
            throw new RuntimeException("Token không tồn tại");
        });
        TokenConfirm tokenConfirm = tokenConfirmRepository.findByToken(token).orElseThrow(() -> {
            throw new RuntimeException("Token không tồn tại");
        });
        if (user.getEnable() && tokenConfirm.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token đã được kích hoạt và còn thời gian");

        } else if (!user.getEnable() && tokenConfirm.getExpiresAt().isBefore(LocalDateTime.now())) {
            tokenConfirm.setConfirmedAt(LocalDateTime.now());
            user.setEnable(true);
            userRepository.save(user);
            tokenConfirmRepository.save(tokenConfirm);
        }else {
            throw new RuntimeException("Token đã hết hạn");
//            tokenConfirm.setConfirmedAt(LocalDateTime.now());

        }
        return null;
    }

    public String register(RegisterRequest request) {
        List<TokenConfirm> tokenConfirms = tokenConfirmRepository.findAll();
//        Faker faker = new Faker();
        validateRegister(request);
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent() && optionalUser.get().getEnable()) {
            throw new UsernameNotFoundException("User được kích hoạt");
        } else if (optionalUser.isPresent() && !optionalUser.get().getEnable()) {
            User user = optionalUser.get();
            List<TokenConfirm> userTokenConfirm = user.getTokenConfirms();
            TokenConfirm tokenConfirm = TokenConfirm.builder()
                    .token(UUID.randomUUID().toString())
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().minusMonths(1))
                    .build();
            userTokenConfirm.add(tokenConfirm);
            tokenConfirmRepository.save(tokenConfirm);
            user.setTokenConfirms(userTokenConfirm);
            user.setEnable(false);
            userRepository.save(user);
        } else {
            TokenConfirm tokenConfirm = TokenConfirm.builder()
                    .token(UUID.randomUUID().toString())
                    .confirmedAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().minusMonths(1))
                    .build();
            tokenConfirmRepository.save(tokenConfirm);//
            List<TokenConfirm> tokenConfirmList = new ArrayList<>();
            tokenConfirmList.add(tokenConfirm);
            User newUser = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .roles(request.getRoleList())
                    .password(request.getPassword())
                    .enable(false)
                    .tokenConfirms(tokenConfirmList)
                    .build();
            userRepository.save(newUser);
        }
        return "Đăng ký thành công";
    }

    private void validateRegister(RegisterRequest request) {
        if (request.getEmail().trim().equals("")) {
            throw new RuntimeException("Email không được để trống");
        }
        if (request.getPassword().trim().length() < 3) {
            throw new RuntimeException("Pass phải hơn 3 ký tự");
        }
        if (request.getName().trim().equals("")) {
            throw new RuntimeException("Tên không được để trống");
        }
    }
}
