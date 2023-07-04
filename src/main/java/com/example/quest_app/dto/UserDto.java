package com.example.quest_app.dto;

import com.example.quest_app.model.User;
import lombok.Data;

@Data
public class UserDto {
    Long id;
    String userName;

    public UserDto (User entity ){
        this.id = entity.getId();
        this.userName = entity.getUserName();


    }
}
