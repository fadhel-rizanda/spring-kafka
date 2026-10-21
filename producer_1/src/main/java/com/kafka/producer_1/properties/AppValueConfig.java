package com.kafka.producer_1.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application") // Notes intellij bakal warning, tinggal tambahin @ConfigurationPropertiesScan di main class
public record AppValueConfig (
        String name,
        String version,
        String description
){
}
