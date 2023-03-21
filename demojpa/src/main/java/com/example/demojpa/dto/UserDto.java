package com.example.demojpa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String name;
    private String email;
}
