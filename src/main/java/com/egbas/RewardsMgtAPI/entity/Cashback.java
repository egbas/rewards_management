package com.egbas.RewardsMgtAPI.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cashback-tbl")
public class Cashback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionID;
    private LocalDate transactionDate;
    private BigDecimal transactionAmount;
    private BigDecimal amountEarned;
    private String Description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
