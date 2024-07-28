package com.suzel.production.ready.features.Production.ready.services;

import com.suzel.production.ready.features.Production.ready.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long id);
}
