package com.example.coursejpa.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourse {
    private String name;
    private String description;
    private List<Integer> categoryId;
    private String type;
    private String thumbnail;
    private Integer price;
    private Double rating;
    private Integer user_id;

}
