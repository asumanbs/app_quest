package com.example.quest_app.dto;

import com.example.quest_app.model.Role;
import com.example.quest_app.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long id;
    String userName;

    Role role;

    public UserDto (User entity ){
        this.id = entity.getId();
        this.userName = entity.getUserName();


    }
}
