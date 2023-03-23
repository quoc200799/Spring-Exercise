package com.example.usermanagermant.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;
    @Column(name = "create at")
    private LocalDateTime createAt;

}
