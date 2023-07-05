package com.example.quest_app.service;

import com.example.quest_app.dto.LikeResponseDto;
import com.example.quest_app.dto.PostCreateDto;
import com.example.quest_app.dto.PostResponseDto;
import com.example.quest_app.model.Post;
import com.example.quest_app.model.User;
import com.example.quest_app.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;
    public PostService(PostRepository postRepository,
                       UserService userService,
                       LikeService likeService){
        this.postRepository = postRepository ;
        this.userService = userService;
        this.likeService = likeService;
    }
    public List<PostResponseDto> getAllPost(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }else
            list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponseDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponseDto(p);}).collect(Collectors.toList());
    }
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
    public Post createPost(PostCreateDto newPost) {
        User user = userService.getUserById(newPost.getUserId());
        if(user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPost.getId());
        toSave.setText(newPost.getText());
        toSave.setTitle(newPost.getTitle());
        toSave.setUser(user);

        return postRepository.save(toSave);
    }
    public Post updatePost(Long postId, Post newPost) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Post foundPost = post.get();
            foundPost.setText(newPost.getText());
            foundPost.setTitle(newPost.getTitle());
            postRepository.save(foundPost);
            return foundPost;
        }else
            return null;
    }
    public void deletePostById(Long postId){
        postRepository.deleteById(postId);
    }

}
