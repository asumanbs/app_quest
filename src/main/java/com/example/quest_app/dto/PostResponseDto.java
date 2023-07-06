package com.example.quest_app.dto;

import com.example.quest_app.model.Post;
import lombok.Data;

import java.util.List;
@Data
public class PostResponseDto {
    Long id;
    Long userId;
    String userName;
    String title;
    String text;



    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();


    }
}
