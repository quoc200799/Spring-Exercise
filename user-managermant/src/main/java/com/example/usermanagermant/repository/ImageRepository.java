package com.example.usermanagermant.repository;

import com.example.usermanagermant.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
