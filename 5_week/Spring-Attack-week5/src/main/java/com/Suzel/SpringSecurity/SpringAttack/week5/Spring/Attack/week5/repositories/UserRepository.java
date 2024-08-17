package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.repositories;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity>  findByEmail(String email);
}
