package com.example.jwt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "identity_card")
public class IdentityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "expried")
    private LocalDateTime expried;

    @Column(name = "issued")
    private LocalDateTime issued;

    @PrePersist
    public void prePersist() {
        issued = LocalDateTime.now().minusYears(2);
        expried = LocalDateTime.now().plusYears(2);
    }
}