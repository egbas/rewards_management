package com.egbas.RewardsMgtAPI.repository;

import com.egbas.RewardsMgtAPI.entity.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {
    List<Cashback> findByCustomer_CustomerID(Long customerID);
}
