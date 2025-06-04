package com.example.loyalty.repository;

import com.example.loyalty.model.LoyaltyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyAccountRepository extends JpaRepository<LoyaltyAccount, String> {
}
