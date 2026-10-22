package com.kafka.producer_1.dtos.response;

import java.util.Set;

public record RegisterResponse(
        String username,
        String email,
        Set<String> roles,
        String token
) {
}
