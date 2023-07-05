package com.example.quest_app.controller;

import com.example.quest_app.dto.PostCreateDto;
import com.example.quest_app.dto.PostResponseDto;
import com.example.quest_app.model.Post;
import com.example.quest_app.model.User;
import com.example.quest_app.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPost(@RequestParam Optional<Long> userId){
        return ResponseEntity.ok(postService.getAllPosts(userId));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));

    }
    @PostMapping
    public Post createPost(@RequestBody PostCreateDto newPost){
        return postService.createPost(newPost);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody Post newPost){
        Post post = postService.updatePost(postId, newPost);
        if(post != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
    }
}
