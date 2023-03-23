package com.example.demouser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    private int id;
    private String username;
    private String email;
    private String password;
    private String avatar;
}
