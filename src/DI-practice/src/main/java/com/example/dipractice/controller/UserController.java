package com.example.dipractice.controller;

import com.example.dipractice.annotation.Controller;
import com.example.dipractice.annotation.Inject;
import com.example.dipractice.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService(){
        return userService;
    }
}
