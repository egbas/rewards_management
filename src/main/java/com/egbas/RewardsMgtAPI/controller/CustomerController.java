package com.egbas.RewardsMgtAPI.controller;

import com.egbas.RewardsMgtAPI.dto.request.LoginRequest;
import com.egbas.RewardsMgtAPI.dto.request.RegisterRequest;
import com.egbas.RewardsMgtAPI.dto.response.ApiResponse;
import com.egbas.RewardsMgtAPI.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/")
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping("register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterRequest registerRequest){
        ApiResponse<?> response = customerService.register(registerRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest){
        ApiResponse<?> response = customerService.login(loginRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
