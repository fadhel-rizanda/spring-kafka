package com.kafka.producer_1.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data // notes embeddable mewajibkan equals() dan hashCode() agar bisa digunakan sebagai composite key
public class OrderProductId {
    private UUID orderId;
    private UUID productId;
}
