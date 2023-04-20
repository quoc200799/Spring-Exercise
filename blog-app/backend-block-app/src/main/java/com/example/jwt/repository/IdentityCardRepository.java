package com.example.jwt.repository;

import com.example.jwt.entity.IdentityCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}