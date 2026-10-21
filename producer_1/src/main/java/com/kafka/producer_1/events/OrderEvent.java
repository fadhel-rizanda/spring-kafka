package com.kafka.producer_1.events;

import com.kafka.producer_1.enums.GeneralStatus;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy.class)
public record OrderEvent(
        UUID orderId,
        GeneralStatus status,
        Long amount
) {
}
