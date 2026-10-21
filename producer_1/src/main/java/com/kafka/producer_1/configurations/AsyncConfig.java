package com.kafka.producer_1.configurations;

import com.kafka.producer_1.properties.AppValueConfig;
import com.kafka.producer_1.services.SmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean("sms")
    public SmsService smsService(){
        return new SmsService();
    }
}
