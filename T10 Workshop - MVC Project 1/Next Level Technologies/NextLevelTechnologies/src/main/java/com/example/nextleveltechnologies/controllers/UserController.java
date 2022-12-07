package com.example.nextleveltechnologies.controllers;

import com.example.nextleveltechnologies.dtos.LoginDTO;
import com.example.nextleveltechnologies.models.users.User;
import com.example.nextleveltechnologies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {

        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogIn(LoginDTO loginDTO) {
        Optional<User> user = userService.login(loginDTO);

        if (user.isPresent()) {
            return "redirect:/home";
        }

        return "user/login";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

}
