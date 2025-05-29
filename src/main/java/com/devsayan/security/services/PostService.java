package com.devsayan.security.services;

import com.devsayan.security.dtos.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto createNewPost(PostDto inputPost);
    PostDto getPostById(Long id);

}
