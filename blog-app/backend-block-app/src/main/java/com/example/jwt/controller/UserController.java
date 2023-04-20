package com.example.jwt.controller;

import com.example.jwt.entity.Blog;
import com.example.jwt.entity.User;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.request.UpsertUserRequest;
import com.example.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Secured({"ROLE_ADMIN"})
    @GetMapping("")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        Page<User> list = userService.getAllUser(page, pageSize);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable Integer id,@RequestBody UpsertUserRequest request) {
        String blog = userService.updateUserById(id,request);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<?> createNewUser(@RequestBody UpsertUserRequest request) {
        String blog = userService.createNewUser(request);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        String blog = userService.deleteUserById(id);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
}
