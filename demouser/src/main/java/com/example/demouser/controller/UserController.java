package com.example.demouser.controller;

import com.example.demouser.db.UserDb;
import com.example.demouser.model.User;
import com.example.demouser.model.dto.UserDto;
import com.example.demouser.model.response.FileResponse;
import com.example.demouser.request.CreateUserRequest;
import com.example.demouser.request.UpdateUserPassword;
import com.example.demouser.request.UpdateUserRequest;
import com.example.demouser.service.UserService;
import com.example.demouser.service.UserServiceimpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        ArrayList<UserDto> usersDtos = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(usersDtos);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable String id) {
        UserDto usersDto = userService.getUsersById(id);
        return ResponseEntity.ok(usersDto);

    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED) //201
    public User createTodo(@Validated @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public User deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // Cập nhật thông tin user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,
                                        @RequestBody UpdateUserRequest request) {
        UserDto userDto = userService.updateUser(id, request);
        return ResponseEntity.ok(userDto);
    }

    // Cập nhật mật khẩu mới
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable String id,
                                            @RequestBody UpdateUserPassword request) {
        userService.updatePassword(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Quên mật khẩu
    @PostMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> updatePassword(@PathVariable String id) {
        String password = userService.forgotPassword(id);
        return ResponseEntity.ok(password);
    }

    // Thay đổi avatar của user
    @PutMapping("/users/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@ModelAttribute("file") MultipartFile file, @PathVariable String id) {
        FileResponse fileResponse = userService.updateAvatar(id, file);
        return ResponseEntity.ok(fileResponse);
    }
}
