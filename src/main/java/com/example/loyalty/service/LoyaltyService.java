package com.example.loyalty.service;

import com.example.loyalty.model.EventType;
import com.example.loyalty.model.LoyaltyAccount;
import com.example.loyalty.model.OrderEvent;
import com.example.loyalty.repository.LoyaltyAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoyaltyService {

    private final LoyaltyAccountRepository repository;

    public LoyaltyService(LoyaltyAccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handleEvent(OrderEvent event) {
        if (event.getEventType() == EventType.ORDER_SHIPPED) {
            credit(event.getCustomerId(), (int) event.getOrderAmount());
        } else if (event.getEventType() == EventType.ORDER_RETURN) {
            debit(event.getCustomerId(), (int) event.getOrderAmount());
        }
    }

    private void credit(String customerId, int points) {
        LoyaltyAccount account = repository.findById(customerId)
                .orElse(new LoyaltyAccount(customerId, 0));
        account.setPoints(account.getPoints() + points);
        repository.save(account);
    }

    private void debit(String customerId, int points) {
        LoyaltyAccount account = repository.findById(customerId)
                .orElse(new LoyaltyAccount(customerId, 0));
        account.setPoints(account.getPoints() - points);
        repository.save(account);
    }
}
