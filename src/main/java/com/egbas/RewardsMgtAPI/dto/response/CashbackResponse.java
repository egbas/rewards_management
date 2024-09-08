package com.egbas.RewardsMgtAPI.dto.response;

import com.egbas.RewardsMgtAPI.entity.Customer;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashbackResponse {
    private Long transactionID;
    private LocalDate transactionDate;
    private BigDecimal amountEarned;
    private String Description;
}
