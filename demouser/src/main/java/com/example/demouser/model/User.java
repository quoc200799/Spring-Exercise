package com.example.demouser.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String password;
}
