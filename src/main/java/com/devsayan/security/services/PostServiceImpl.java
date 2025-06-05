package com.devsayan.security.services;

import com.devsayan.security.dtos.PostDto;
import com.devsayan.security.entities.PostEntity;
import com.devsayan.security.entities.User;
import com.devsayan.security.exceptions.ResourceNotFoundException;
import com.devsayan.security.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@ToString
public class PostServiceImpl implements PostService{
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .toList();
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity savedPost = postRepository
                .save(modelMapper.map(inputPost,PostEntity.class));
        return modelMapper.map(savedPost,PostDto.class);

    }
    @Override
    public PostDto getPostById(Long id){
        User user =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user :{} ",user);
        PostEntity returnedPost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: " + id));
        return modelMapper.map(returnedPost,PostDto.class);
    }
}
