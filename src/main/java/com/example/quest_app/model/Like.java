package com.example.quest_app.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "p_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    Long postId;

}
