package com.kafka.consumer_1.consumer;

import com.kafka.consumer_1.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {
    @KafkaListener(topics = "order-paid", groupId = "consumer-1-group")
    public void handle(OrderEvent event) {
        System.out.println("Consumer 1 Received: " + event);
    }

//    @KafkaListener(topics = "order-paid", groupId = "consumer-1-group-1")
//    public void handleJuga(OrderEvent event) {
//        System.out.println("Consumer 1-1 Received: " + event);
//    }
}
