package com.example.quest_app.service;


import com.example.quest_app.dto.UserDto;
import com.example.quest_app.model.User;
import com.example.quest_app.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    public User createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public UserDto getUser(String userName) {
        var savedUser  = getOneUserByUserName(userName);
        return UserDto.builder()
                .userName(savedUser.getUserName())
                .role(savedUser.getRole())
                .build();

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
        }catch(EmptyResultDataAccessException e) {
            System.out.println("User "+userId+" doesn't exist");
        }
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }
}


