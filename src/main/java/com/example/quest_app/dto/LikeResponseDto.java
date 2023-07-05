package com.example.quest_app.dto;

import com.example.quest_app.model.Like;
import lombok.Data;

@Data
public class LikeResponseDto {
    Long id;
    Long userId;
    Long postId;

    public LikeResponseDto(Like entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}

