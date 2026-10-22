package com.kafka.producer_1.dtos.request;

public record RegisterRequest(
        String username,
        String email,
        String password
) {
}
