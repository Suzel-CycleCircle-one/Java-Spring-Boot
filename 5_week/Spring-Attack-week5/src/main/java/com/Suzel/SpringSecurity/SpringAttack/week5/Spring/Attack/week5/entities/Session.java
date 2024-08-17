package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private  String refreshToken;

    @CreationTimestamp
    private LocalDateTime lastUsedAt;

   @ManyToOne
    private UserEntity user;
}
