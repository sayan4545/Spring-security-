package com.devsayan.security.controlers;

import com.devsayan.security.dtos.PostDto;
import com.devsayan.security.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @GetMapping("/posts")
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/posts")
    public PostDto savePost(@RequestBody PostDto inputPost) {
        return postService.createNewPost(inputPost);
    }
    @GetMapping("/posts/{id}")
    public PostDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }


}
