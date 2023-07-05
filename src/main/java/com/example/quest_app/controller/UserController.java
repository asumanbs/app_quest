package com.example.quest_app.controller;

import com.example.quest_app.dto.UserDto;
import com.example.quest_app.model.User;
import com.example.quest_app.service.UserService;
import com.example.quest_app.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private  UserService userService;
    public UserController (UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User newUser){
        User user = userService.createUser(newUser);
        if(user != null)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/{userId}")
    public UserDto getOneUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if(user == null) {
            throw new UserNotFoundException();
        }
        return new UserDto(user);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        User user = userService.updateOneUser(userId, newUser);
        if(user != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

}
