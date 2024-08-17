package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}

