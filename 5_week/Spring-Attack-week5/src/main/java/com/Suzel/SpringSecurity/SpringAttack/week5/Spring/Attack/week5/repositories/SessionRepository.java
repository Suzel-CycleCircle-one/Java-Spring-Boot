package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.repositories;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.Session;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(UserEntity userEntity);

    Optional<Session> findByRefreshToken(String refreshToken);
}
