package com.egbas.RewardsMgtAPI.dto.request;

import com.egbas.RewardsMgtAPI.entity.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {
    private LocalDate transactionDate;
    private BigDecimal transactionAmount;
//    private double amountEarned;
    private String Description;
//    private Customer customer;
}
