package com.example.quest_app.service;

import com.example.quest_app.dto.LikeCreateRequest;
import com.example.quest_app.dto.LikeResponseDto;
import com.example.quest_app.model.Like;
import com.example.quest_app.model.Post;
import com.example.quest_app.model.User;
import com.example.quest_app.repository.LikeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@CacheConfig(cacheNames = {"quest"})
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;
    public LikeService(LikeRepository likeRepository,
                       UserService userService,
                       PostService postService){
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }
    public List<LikeResponseDto> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like -> new LikeResponseDto(like)).collect(Collectors.toList());
    }
    //@Cacheable(key = "#LikeId")
    public Like getOneLikeById(Long LikeId) {
        return likeRepository.findById(LikeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if(user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    public void deleteLikeById(Long postId){
        likeRepository.deleteById(postId);
    }

}
