package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.controllers;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.PostDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
//    @PreAuthorize("hasAnyRole('USER' , 'ADMIN') OR hasAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

}
