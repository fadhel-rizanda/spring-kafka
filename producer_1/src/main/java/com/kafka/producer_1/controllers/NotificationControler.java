package com.kafka.producer_1.controllers;

import com.kafka.producer_1.properties.AppValueConfig;
import com.kafka.producer_1.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationControler {
    private final NotificationService sms;
    private final NotificationService email;
    private final AppValueConfig appValueConfig;

    public NotificationControler(@Qualifier("sms") NotificationService sms, NotificationService email, AppValueConfig appValueConfig) {
        this.sms = sms;
        this.email = email;
        this.appValueConfig = appValueConfig;
    }

    @PostMapping("/send")
    public Map<String, String> send(@RequestParam String message) {
        return Map.of(
                "sms", sms.send(message),
                "email", email.send(message),
                "appValueConfig", appValueConfig.name()
        );
    }
}
