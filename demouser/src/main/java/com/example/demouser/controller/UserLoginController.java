package com.example.demouser.controller;

import com.example.demouser.model.dto.UserLoginDto;
import com.example.demouser.service.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class UserLoginController {
@Autowired
    private UserLoginService userLoginService;
    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLogin(@RequestParam String username, @RequestParam String password) {
        // (required = false) the hien gia tri day co hay khong cung duoc
        UserLoginDto usersDtos = userLoginService.getUsersById(username, password);
        return ResponseEntity.status(HttpStatus.OK).body(usersDtos);
    }
}
