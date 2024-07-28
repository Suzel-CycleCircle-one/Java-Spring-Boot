package com.suzel.production.ready.features.Production.ready.services;

import com.suzel.production.ready.features.Production.ready.dto.PostDTO;
import com.suzel.production.ready.features.Production.ready.entities.PostEnity;
import com.suzel.production.ready.features.Production.ready.exceptions.ResourceNotFoundException;
import com.suzel.production.ready.features.Production.ready.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements  PostService{

    private  final PostRepository postRepository;
    private  final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postEnity -> modelMapper.map(postEnity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEnity postEnity = modelMapper.map(inputPost , PostEnity.class);
        postEnity = postRepository.save(postEnity);
       return modelMapper.map(postEnity, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {
        PostEnity postEnity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post Not Found this id + " + id));
        return modelMapper.map(postEnity ,PostDTO.class);
    }
}
