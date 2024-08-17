package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.utils;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.PostDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.UserEntity;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private  final PostService postService;

  public  boolean isOwnerOfPost(Long postId){
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());
    }
}
