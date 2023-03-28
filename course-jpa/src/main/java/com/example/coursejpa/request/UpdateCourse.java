package com.example.coursejpa.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCourse {
    private String name;
    private String description;
    private String type;
    private List<Integer> categoryId;
    private String thumbnail;
    private Integer price;
    private Integer user_id;
}
