package com.example.quest_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long postId;
    Long userId;
    @Lob
    @Column(columnDefinition = "text")
    String text;
}
