package com.example.quest_app.dto;

import com.example.quest_app.model.Comment;
import lombok.Data;

@Data
public class CommentResponseDto {
    Long id;
    Long userId;
    String userName;
    String text;

    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.text = entity.getText();
    }
}
