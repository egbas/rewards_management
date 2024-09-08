package com.egbas.RewardsMgtAPI.dto.response;
import lombok.*;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRewardResponse {
    private Long customerID;
    private BigDecimal totalCashBack;
    private BigDecimal currentBalance;
}
