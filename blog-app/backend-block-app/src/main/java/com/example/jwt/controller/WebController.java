package com.example.jwt.controller;

import com.example.jwt.entity.Blog;
import com.example.jwt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("Home");
    }

    @GetMapping("user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("User");
    }


    @GetMapping("admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Admin");
    }

    @GetMapping("author")
    public ResponseEntity<?> getAuthor() {
        return ResponseEntity.ok("Author");
    }
}
