package com.kafka.producer_1.enums;

import lombok.Getter;

@Getter
public enum GeneralStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String status;

    GeneralStatus(String status) {
        this.status = status;
    }

}
