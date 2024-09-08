package com.egbas.RewardsMgtAPI.controller;

import com.egbas.RewardsMgtAPI.dto.request.RegisterRequest;
import com.egbas.RewardsMgtAPI.dto.request.TransactionRequest;
import com.egbas.RewardsMgtAPI.dto.request.WithdrawalRequest;
import com.egbas.RewardsMgtAPI.dto.response.ApiResponse;
import com.egbas.RewardsMgtAPI.dto.response.CashbackResponse;
import com.egbas.RewardsMgtAPI.service.CashbackService;
import com.egbas.RewardsMgtAPI.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rewards/")
public class RewardsController {

    private final CashbackService cashbackService;
    private final CustomerService customerService;
    @PostMapping("transaction/{id}")
    public ResponseEntity<ApiResponse<?>> transaction(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest){
        ApiResponse<?> response = cashbackService.transaction(id, transactionRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("history/{customerID}")
    public ResponseEntity<List<CashbackResponse>> cashbackHistory(@PathVariable Long customerID){
        List<CashbackResponse> response = cashbackService.cashbackHistory(customerID);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("balance/{id}")
    public ResponseEntity<ApiResponse<?>> customerRewards(@PathVariable Long id){
        ApiResponse<?> response = customerService.customerRewards(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("withdrawal/{id}")
    public ResponseEntity<ApiResponse<?>> cashbackWithdrawal(@PathVariable Long id, @RequestBody WithdrawalRequest withdrawalRequest){
        ApiResponse<?> response = customerService.cashbackWithdrawal(id, withdrawalRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
