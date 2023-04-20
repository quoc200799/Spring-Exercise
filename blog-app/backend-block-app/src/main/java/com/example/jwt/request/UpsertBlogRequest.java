package com.example.jwt.request;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertBlogRequest {
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private Boolean status;
    private List<Integer> categoryIds; // Danh sách id của các category áp dụng
}
