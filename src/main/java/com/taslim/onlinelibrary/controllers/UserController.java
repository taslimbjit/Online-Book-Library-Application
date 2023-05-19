package com.taslim.onlinelibrary.controllers;

import com.taslim.onlinelibrary.model.UserRequestModel;
import com.taslim.onlinelibrary.model.AuthenticationResponse;
import com.taslim.onlinelibrary.model.AuthenticationRequest;
import com.taslim.onlinelibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel){
        return userService.register(requestModel);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        return userService.login(authenticationRequest);
    }

}