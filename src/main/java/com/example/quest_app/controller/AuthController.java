package com.example.quest_app.controller;

import com.example.quest_app.dto.LoginReqDto;
import com.example.quest_app.dto.TokenResponseDto;
import com.example.quest_app.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginReqDto loginReqDto){
        return ResponseEntity.ok(authService.login(loginReqDto));

    }

}
