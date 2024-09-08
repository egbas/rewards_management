package com.egbas.RewardsMgtAPI.service.serviceImpl;

import com.egbas.RewardsMgtAPI.config.JwtService;
import com.egbas.RewardsMgtAPI.dto.request.LoginRequest;
import com.egbas.RewardsMgtAPI.dto.request.RegisterRequest;
import com.egbas.RewardsMgtAPI.dto.request.WithdrawalRequest;
import com.egbas.RewardsMgtAPI.dto.response.ApiResponse;
import com.egbas.RewardsMgtAPI.dto.response.CustomerRewardResponse;
import com.egbas.RewardsMgtAPI.entity.Customer;
import com.egbas.RewardsMgtAPI.repository.CustomerRepository;
import com.egbas.RewardsMgtAPI.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        Customer user = Customer.builder()
                .email(registerRequest.getEmail())
                .totalCashBack(BigDecimal.ZERO)
                .currentBalance(BigDecimal.ZERO)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .status("success")
                .message("Registration successful")
                .statusCode(HttpStatus.CREATED)
                .build();
    }

    @Override
    public ApiResponse<?> login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword())
        );

        Customer savedUser = userRepository.findByEmail(loginRequest.getEmail()).get();
        String jwtToken = jwtService.generateToken(savedUser);

        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", jwtToken);

        return ApiResponse.<Map<String, Object>>builder()
                .status("success")
                .message("Login successful")
                .data(data)
                .statusCode(HttpStatus.OK)
                .build();
    }

    @Override
    public ApiResponse<?> cashbackWithdrawal(Long id, WithdrawalRequest withdrawalRequest) {
        Customer user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        user.setCurrentBalance(user.getCurrentBalance().subtract(withdrawalRequest.getWithdrawalAmount()));
        userRepository.save(user);

        CustomerRewardResponse response = CustomerRewardResponse.builder()
                .customerID(user.getCustomerID())
                .totalCashBack(user.getTotalCashBack())
                .currentBalance(user.getCurrentBalance())
                .build();

        return ApiResponse.<CustomerRewardResponse>builder()
                .status("success")
                .message("Withdrawal successful")
                .data(response)
                .statusCode(HttpStatus.OK)
                .build();

    }

    @Override
    public ApiResponse<?> customerRewards(Long id) {
        Customer user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        CustomerRewardResponse response = CustomerRewardResponse.builder()
                .customerID(user.getCustomerID())
                .totalCashBack(user.getTotalCashBack())
                .currentBalance(user.getCurrentBalance())
                .build();

        return ApiResponse.<CustomerRewardResponse>builder()
                .status("success")
                .data(response)
                .statusCode(HttpStatus.OK)
                .build();
    }
}
