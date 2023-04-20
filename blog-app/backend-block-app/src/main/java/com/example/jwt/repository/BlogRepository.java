package com.example.jwt.repository;

import com.example.jwt.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleIgnoreCase(String title);

//    List<Blog> findByCategories_Id(Integer id);

    long countByCategories_Id(Integer id);

    List<Blog> findByCategories_Name(String name);

    List<Blog> findByCategories_NameIgnoreCase(String name);

    Optional<Blog> findByIdAndSlug(Integer id, String slug);

    Page<Blog> findByTitle(String title, Pageable pageable);

    Page<Blog> findByUser_Email(String email, Pageable pageable);






}