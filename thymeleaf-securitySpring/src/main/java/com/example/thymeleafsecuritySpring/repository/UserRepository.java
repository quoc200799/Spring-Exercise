package com.example.thymeleafsecuritySpring.repository;

import com.example.thymeleafsecuritySpring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByTokenConfirms_Token(String token);


}