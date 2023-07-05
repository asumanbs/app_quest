package com.example.quest_app.dto;

import com.example.quest_app.model.Post;

import java.util.List;

public class PostResponseDto {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponseDto> postLikes;


    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();


    }
}
