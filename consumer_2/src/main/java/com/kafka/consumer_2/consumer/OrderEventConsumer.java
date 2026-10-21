package com.kafka.consumer_2.consumer;

import com.kafka.consumer_2.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {
    @KafkaListener(topics = "order-paid", groupId = "consumer-2-group")
    public void handle(OrderEvent event) {
        System.out.println("Consumer 2 Received: " + event);
    }
}
