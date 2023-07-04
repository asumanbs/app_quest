package com.example.quest_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;

}
