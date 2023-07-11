package com.example.quest_app.config;

import com.example.quest_app.model.Role;
import com.example.quest_app.service.UserService;
import org.springframework.boot.CommandLineRunner;
import com.example.quest_app.model.User;
import org.springframework.stereotype.Component;


import java.security.cert.CertPathBuilder;
@Component
public class StartupConfig implements CommandLineRunner {
    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(User.builder().role(Role.ADMIN).userName("asu").password("pass").build());
        userService.createUser(User.builder().role(Role.USER).userName("abc").password("pass2345").build());
    }

}
