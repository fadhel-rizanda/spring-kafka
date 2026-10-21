package com.kafka.producer_1.dtos;

import com.kafka.producer_1.enums.GeneralStatus;

import java.util.UUID;

public record ProductDto(
        UUID id,
        String name,
        GeneralStatus status,
        Long amount,
        Double price
) {
}
