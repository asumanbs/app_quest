package com.example.quest_app.repository;

import com.example.quest_app.dto.UserDto;
import com.example.quest_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);


}
