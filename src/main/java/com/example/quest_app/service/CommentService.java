package com.example.quest_app.service;

import com.example.quest_app.dto.CommentCreateDto;
import com.example.quest_app.dto.CommentResponseDto;
import com.example.quest_app.dto.CommentUpdateDto;
import com.example.quest_app.model.Comment;
import com.example.quest_app.model.Post;
import com.example.quest_app.model.User;
import com.example.quest_app.repository.CommentRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"quest"})
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService,
                          PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }


    public List<CommentResponseDto> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments = commentRepository.findAll();
        return comments.stream().map(comment -> new CommentResponseDto(comment)).collect(Collectors.toList());
    }

    public Comment createOneComment(CommentCreateDto request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if(user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        }else
            return null;
    }
   @Cacheable(key = "#commentId")
    public Comment getOneCommentById(Long commentId) {
        logger.info(String.format("Get comment by id " + commentId));
        return commentRepository.findById(commentId).orElse(null);
    }
    public Comment updateOneCommentById(Long commentId, CommentUpdateDto request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
    logger.info(String.format("deleted comment " + commentId));
        commentRepository.deleteById(commentId);
    }
    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "10000")
    public void clearCache(){
        logger.info("Caches are cleared");
    }

}
