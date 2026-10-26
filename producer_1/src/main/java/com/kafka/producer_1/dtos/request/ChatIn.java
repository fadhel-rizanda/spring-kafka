package com.kafka.producer_1.dtos.request;

import java.util.UUID;

public record ChatIn(
        UUID senderId,
        String content
) {
}
