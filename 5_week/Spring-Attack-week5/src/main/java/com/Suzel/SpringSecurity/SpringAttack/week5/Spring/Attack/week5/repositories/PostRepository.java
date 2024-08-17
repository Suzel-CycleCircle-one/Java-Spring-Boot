package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.repositories;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}

