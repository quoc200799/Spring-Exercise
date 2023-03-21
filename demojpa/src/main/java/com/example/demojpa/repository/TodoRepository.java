package com.example.demojpa.repository;

import com.example.demojpa.entity.Todo;
import com.example.demojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Optional<Todo> findByTitle(String title);

}
