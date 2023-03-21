package com.example.demojpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
@ToString
public class User {
    @Id
    @Column(name = "id", unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    // unique là không được trùng nếu true
    // nullable là bắt buộc phải có

}
