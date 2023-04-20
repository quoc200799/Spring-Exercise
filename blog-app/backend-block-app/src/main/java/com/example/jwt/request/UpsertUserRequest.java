package com.example.jwt.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertUserRequest {
    private String name;
    private String email;
    private String password;
    private List<Integer> roleIds; // Danh sách id của các role áp dụng
}
