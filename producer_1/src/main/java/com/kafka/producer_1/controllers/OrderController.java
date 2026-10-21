package com.kafka.producer_1.controllers;

import com.kafka.producer_1.events.OrderEvent;
import com.kafka.producer_1.models.Order;
import com.kafka.producer_1.producers.OrderEventProducer;
import com.kafka.producer_1.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderEventProducer producer;
    private final OrderService orderService;

    @PostMapping("/send")
    public String send(@RequestBody OrderEvent event) {
        System.out.println("Sending message to Kafka topic: orderId=" + event.orderId() + ", event=" + event);
        orderService.createOrder(event);
        producer.sendMessage(event);
        return "Message sent to Kafka topic";
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderEvent>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        Order order = orderService.findById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
