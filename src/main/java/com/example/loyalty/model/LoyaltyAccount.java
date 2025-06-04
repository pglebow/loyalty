package com.example.loyalty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoyaltyAccount {
    @Id
    private String customerId;

    private int points;

    public LoyaltyAccount() {}

    public LoyaltyAccount(String customerId, int points) {
        this.customerId = customerId;
        this.points = points;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
