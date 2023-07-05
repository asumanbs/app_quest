package com.example.quest_app.controller;

import com.example.quest_app.dto.LikeCreateRequest;
import com.example.quest_app.dto.LikeResponseDto;
import com.example.quest_app.model.Like;
import com.example.quest_app.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<List<LikeResponseDto>> getAllLikes(@RequestParam Optional<Long> userId,
                                                             @RequestParam Optional<Long> postId) {
        return ResponseEntity.ok(likeService.getAllLikesWithParam(userId, postId));
    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request) {
        return likeService.createOneLike(request);
    }
    @DeleteMapping("/{likeId}")
    public void  deleteLikeById(@PathVariable Long likeId){
        likeService.deleteLikeById(likeId);
    }
}
