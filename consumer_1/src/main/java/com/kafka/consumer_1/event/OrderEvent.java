package com.kafka.consumer_1.event;

import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderEvent(
        String orderId,
        String status,
        long amount
) {
}
