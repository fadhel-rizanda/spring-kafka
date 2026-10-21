package com.kafka.producer_1.configurations;

import com.kafka.producer_1.constants.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic productCreatedTopic() {
        return TopicBuilder
                .name(KafkaTopics.ORDER_PAID)
                .partitions(3)
                .replicas(1)
                .compact() // Enable log compaction for the topic
                .build();
    }
}
