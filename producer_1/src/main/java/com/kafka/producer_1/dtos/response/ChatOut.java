package com.kafka.producer_1.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChatOut(
        UUID id,
        UUID roomId,
        UUID senderId,
        String senderName,
        String content,
        LocalDateTime createdAt
) {
}