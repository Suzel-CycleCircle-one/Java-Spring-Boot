package com.suzel.production.ready.features.Production.ready.controllers;

import com.suzel.production.ready.features.Production.ready.dto.PostDTO;
import com.suzel.production.ready.features.Production.ready.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

      private  final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPost(){
        List<PostDTO> postDTO = postService.getAllPosts();
        return ResponseEntity.ok(postDTO);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        PostDTO postDTO1 = postService.createNewPost(postDTO);
        return ResponseEntity.ok(postDTO1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        PostDTO postDTO = postService.getPostById(id);
        return ResponseEntity.ok(postDTO);
    }
}
