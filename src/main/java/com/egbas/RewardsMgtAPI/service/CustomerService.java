package com.egbas.RewardsMgtAPI.service;

import com.egbas.RewardsMgtAPI.dto.request.LoginRequest;
import com.egbas.RewardsMgtAPI.dto.request.RegisterRequest;
import com.egbas.RewardsMgtAPI.dto.request.WithdrawalRequest;
import com.egbas.RewardsMgtAPI.dto.response.ApiResponse;

public interface CustomerService {

    ApiResponse<?> register(RegisterRequest registerRequest);
    ApiResponse<?> login(LoginRequest loginRequest);
    ApiResponse<?> customerRewards(Long id);
    ApiResponse<?> cashbackWithdrawal(Long id, WithdrawalRequest withdrawalRequest);
}
