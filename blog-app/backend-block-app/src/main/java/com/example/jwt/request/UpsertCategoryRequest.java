package com.example.jwt.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertCategoryRequest {
    private String name;
}
