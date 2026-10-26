package com.kafka.producer_1.controllers;

import com.kafka.producer_1.dtos.request.ChatIn;
import com.kafka.producer_1.dtos.response.ChatOut;
import com.kafka.producer_1.handler.ChatHandler;
import com.kafka.producer_1.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final ChatService chatService;
    private final ChatHandler chatHandler;

    @GetMapping("/chat/{id}/messages")
    public List<ChatOut> history(@PathVariable UUID id) {
        return chatService.history(id);
    }

    @PostMapping("/chat/{id}/messages")
    public ChatOut send(@PathVariable UUID id, @RequestBody ChatIn in) throws IOException {
        ChatOut dto = chatService.send(id, in.senderId(), in.content());
        chatHandler.broadcast(id, dto);
        return dto;
    }
}
