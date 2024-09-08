package com.egbas.RewardsMgtAPI.service;

import com.egbas.RewardsMgtAPI.dto.request.TransactionRequest;
import com.egbas.RewardsMgtAPI.dto.response.ApiResponse;
import com.egbas.RewardsMgtAPI.dto.response.CashbackResponse;

import java.util.List;

public interface CashbackService {

    ApiResponse<?> transaction(Long id, TransactionRequest transactionRequest);
    List<CashbackResponse> cashbackHistory(Long customerID);

}
