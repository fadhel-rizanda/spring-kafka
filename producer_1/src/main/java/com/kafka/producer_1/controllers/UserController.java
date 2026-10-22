package com.kafka.producer_1.controllers;

import com.kafka.producer_1.dtos.request.RegisterRequest;
import com.kafka.producer_1.dtos.response.RegisterResponse;
import com.kafka.producer_1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public RegisterResponse login(@RequestBody RegisterRequest registerRequest) {
        return userService.loginUser(registerRequest);
    }
}
