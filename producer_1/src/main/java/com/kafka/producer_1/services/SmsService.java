package com.kafka.producer_1.services;

import org.springframework.stereotype.Service;

// @Service ga wajib bsiia bikin manual lewat config, contoh di class AsyncConfig
public class SmsService extends NotificationService {
    @Override
    public String send(String message) {
        return "[Sms]: "+message;
    }
}