package com.example.loyalty.service;

import com.example.loyalty.model.EventType;
import com.example.loyalty.model.LoyaltyAccount;
import com.example.loyalty.model.OrderEvent;
import com.example.loyalty.repository.LoyaltyAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoyaltyServiceTest {

    private LoyaltyAccountRepository repository;
    private LoyaltyService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(LoyaltyAccountRepository.class);
        service = new LoyaltyService(repository);
    }

    @Test
    void creditsPointsWhenOrderShippedEventReceived() {
        OrderEvent event = new OrderEvent();
        event.setEventType(EventType.ORDER_SHIPPED);
        event.setCustomerId("cust1");
        event.setOrderAmount(10.0);

        when(repository.findById("cust1")).thenReturn(Optional.of(new LoyaltyAccount("cust1", 0)));
        when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        service.handleEvent(event);
        Mockito.verify(repository).save(Mockito.argThat(acc -> acc.getPoints() == 10));
    }

    @Test
    void debitsPointsWhenOrderReturnEventReceived() {
        OrderEvent event = new OrderEvent();
        event.setEventType(EventType.ORDER_RETURN);
        event.setCustomerId("cust1");
        event.setOrderAmount(5.0);

        when(repository.findById("cust1")).thenReturn(Optional.of(new LoyaltyAccount("cust1", 10)));
        when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        service.handleEvent(event);
        Mockito.verify(repository).save(Mockito.argThat(acc -> acc.getPoints() == 5));
    }
}
