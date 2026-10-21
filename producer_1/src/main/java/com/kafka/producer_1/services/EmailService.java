package com.kafka.producer_1.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailService extends NotificationService {
    @Override
    public String send(String message) {
        return "[Email]: "+message;
    }
}
