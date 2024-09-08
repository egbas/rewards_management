package com.egbas.RewardsMgtAPI.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private BigDecimal totalCashBack;
    private BigDecimal currentBalance;
    private String password;
}
