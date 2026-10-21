package com.kafka.producer_1.producers;

import com.kafka.producer_1.constants.KafkaTopics;
import com.kafka.producer_1.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(OrderEvent event) {
        kafkaTemplate.send(KafkaTopics.ORDER_PAID, event.orderId().toString(), event)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.err.printf(" [PRODUCER 1] Failed to send data with key=%s value=%s due to: %s%n",
                                event.orderId(), event, exception.getMessage());
                    } else {
                        System.out.printf(" [PRODUCER 1] Sent message with key=%s value=%s to partition=%d offset=%d%n",
                                event.orderId(), event, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                    }
                });
    }
}
