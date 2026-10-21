package com.kafka.producer_1.services;

import com.kafka.producer_1.enums.GeneralStatus;
import com.kafka.producer_1.events.OrderEvent;
import com.kafka.producer_1.exception.NotFoundException;
import com.kafka.producer_1.models.Order;
import com.kafka.producer_1.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderEvent> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderEvent(order.getId(), order.getStatus(), order.getAmount()))
                .toList();
    }

    public Order createOrder(OrderEvent order) {
        Order newOrder = new Order();
        newOrder.setStatus(order.status());
        newOrder.setAmount(order.amount());
        orderRepository.save(newOrder);
        return newOrder;
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
    }

    public Order updateOrderStatus(UUID id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        GeneralStatus generalStatus;
        try {
            generalStatus = (status != null) ? GeneralStatus.valueOf(status.toUpperCase()) : GeneralStatus.PENDING;
        } catch (IllegalArgumentException e) {
            generalStatus = GeneralStatus.PENDING; // Fallback ke PENDING jika string tidak valid
        }

        order.setStatus(generalStatus);
        orderRepository.save(order);
        return order;
    }
}
