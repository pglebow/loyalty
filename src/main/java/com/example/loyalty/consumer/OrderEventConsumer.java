package com.example.loyalty.consumer;

import com.example.loyalty.model.OrderEvent;
import com.example.loyalty.service.LoyaltyService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    private final LoyaltyService loyaltyService;

    public OrderEventConsumer(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @KafkaListener(topics = "order-events", groupId = "loyalty-service")
    public void listen(@Payload OrderEvent event) {
        loyaltyService.handleEvent(event);
    }
}
