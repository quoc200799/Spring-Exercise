package com.example.jwt.dto;

import com.example.jwt.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String avatar;

    private List<Role> roles;
}
