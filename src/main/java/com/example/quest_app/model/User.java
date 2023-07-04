package com.example.quest_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    Long id;
    String userName;
    String password;

}
