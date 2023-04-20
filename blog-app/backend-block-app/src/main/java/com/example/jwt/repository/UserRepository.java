package com.example.jwt.repository;

import com.example.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByRoles_NameIn(List<String> admin);

    List<User> findByRoles_NameIn(Collection<String> names);
}