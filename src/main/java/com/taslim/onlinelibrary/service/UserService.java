package com.taslim.onlinelibrary.service;

import com.taslim.onlinelibrary.model.AuthenticationRequest;
import com.taslim.onlinelibrary.model.AuthenticationResponse;
import com.taslim.onlinelibrary.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> register(UserRequestModel requestModel);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}

