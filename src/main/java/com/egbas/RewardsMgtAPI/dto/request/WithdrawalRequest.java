package com.egbas.RewardsMgtAPI.dto.request;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalRequest {

    private BigDecimal withdrawalAmount;
}
