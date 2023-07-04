package com.example.quest_app.service;

import com.example.quest_app.dto.UserDto;
import com.example.quest_app.model.User;
import com.example.quest_app.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }
    public void deleteById(Long userId) {
        try {
            userRepository.deleteById(userId);
        }catch(EmptyResultDataAccessException e) { //user zaten yok, db'den empty result gelmiş
            System.out.println("User "+userId+" doesn't exist"); //istersek loglayabiliriz
        }
    }
}
