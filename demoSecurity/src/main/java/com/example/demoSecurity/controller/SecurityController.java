package com.example.demoSecurity.controller;

import com.example.demoSecurity.security.AuthenticationFacade;
import com.example.demoSecurity.security.anotation.IsUser;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("Home");
    }
    @PreAuthorize("hasAnyRole('SALE','ADMIN')")
    @GetMapping("dashboard")
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok("dashboard");
    }
    @GetMapping("user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("User");
    }
    @GetMapping("category")
    public ResponseEntity<?> getCategory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }
    @Secured({ "ROLE_SALE", "ROLE_ADMIN" })
    @GetMapping("product")
    public ResponseEntity<?> getProduct() {
        return ResponseEntity.ok(authenticationFacade.getAuthentication());
    }

    @RolesAllowed({ "ROLE_SALE", "ROLE_ADMIN" })
    @GetMapping("brand")
    public ResponseEntity<?> getBrand(Principal principal) {
        return ResponseEntity.ok(principal);
    }
    @GetMapping("order")
    public ResponseEntity<?> getOrder(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }
    @PostAuthorize("hasAnyRole('SALE','ADMIN')")
    @GetMapping("posts")
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok("posts");
    }
    @IsUser
    @GetMapping("new-user")
    public ResponseEntity<?> getAndUpdateUser() {
        return ResponseEntity.ok("get and update");
    }
}
