package com.example.jwt.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private Long used;// Số bài blog được áp dụng category này
}
