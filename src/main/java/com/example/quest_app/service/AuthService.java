package com.example.quest_app.service;

import com.example.quest_app.dto.LoginReqDto;
import com.example.quest_app.dto.TokenResponseDto;
import com.example.quest_app.exception.GenericException;
import com.example.quest_app.exception.UserNotFoundException;
import com.example.quest_app.utils.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;

@Service
public class AuthService {
    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginReqDto loginReqDto) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReqDto.getUserName(), loginReqDto.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(auth))
                    .userDto(userService.getUser(loginReqDto.getUserName()))
                    .build();

        }catch(Exception e){
            throw new UserNotFoundException("user not found");

        }
    }
}
