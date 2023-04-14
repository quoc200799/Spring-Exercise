package com.example.thymeleafsecuritySpring.request;

import com.example.thymeleafsecuritySpring.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String name;
    private String password;
    private List<Role> roleList;
}
