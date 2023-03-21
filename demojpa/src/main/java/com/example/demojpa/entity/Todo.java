package com.example.demojpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Todo {
    @Id
    @Column(name = "id", unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;
    @Column(name = "status", columnDefinition="tinyint(1) default 1")
    private boolean status;

}
