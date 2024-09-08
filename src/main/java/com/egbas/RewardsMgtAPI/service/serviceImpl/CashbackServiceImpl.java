package com.egbas.RewardsMgtAPI.service.serviceImpl;

import com.egbas.RewardsMgtAPI.dto.request.TransactionRequest;
import com.egbas.RewardsMgtAPI.dto.response.ApiResponse;
import com.egbas.RewardsMgtAPI.dto.response.CashbackResponse;
import com.egbas.RewardsMgtAPI.entity.Cashback;
import com.egbas.RewardsMgtAPI.entity.Customer;
import com.egbas.RewardsMgtAPI.repository.CashbackRepository;
import com.egbas.RewardsMgtAPI.repository.CustomerRepository;
import com.egbas.RewardsMgtAPI.service.CashbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CashbackServiceImpl implements CashbackService {

    private final CashbackRepository cashbackRepository;
    private final CustomerRepository customerRepository;
    @Override
    public ApiResponse<?> transaction(Long id, TransactionRequest transactionRequest) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        BigDecimal transactionAmount = transactionRequest.getTransactionAmount();
        BigDecimal amountEarned;

        // Algorithm to calculate amount earned
        if (transactionAmount.compareTo(BigDecimal.valueOf(10000)) < 0) {
            amountEarned = transactionAmount.multiply(BigDecimal.valueOf(0.20));
        } else {
            amountEarned = transactionAmount.multiply(BigDecimal.valueOf(0.10));
        }

        Cashback transaction = Cashback.builder()
                .transactionDate(LocalDate.now())
                .transactionAmount(transactionAmount)
                .amountEarned(amountEarned)
                .Description(transactionRequest.getDescription())
                .customer(customer)
                .build();

        cashbackRepository.save(transaction);

        customer.setTotalCashBack(customer.getTotalCashBack().add(amountEarned));  // Adding to existing totalCashBack
        customer.setCurrentBalance(customer.getCurrentBalance().add(amountEarned)); // Adding to existing currentBalance

        customerRepository.save(customer);

        return ApiResponse.builder()
                .status("success")
                .message("Transaction successful")
                .statusCode(HttpStatus.CREATED)
                .build();
    }

    @Override
    public List<CashbackResponse> cashbackHistory(Long customerID) {
        List<Cashback> cashbacks = cashbackRepository.findByCustomer_CustomerID(customerID);

        return cashbacks.stream()
                .map(this::convertToTaskResponse)
                .collect(Collectors.toList());


    }

    private CashbackResponse convertToTaskResponse(Cashback cashback) {
        return new CashbackResponse(
                cashback.getTransactionID(),
                cashback.getTransactionDate(),
                cashback.getAmountEarned(),
                cashback.getDescription()
        );
    }
}
