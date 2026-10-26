package com.kafka.producer_1.handler;

import com.kafka.producer_1.dtos.request.ChatIn;
import com.kafka.producer_1.dtos.response.ChatOut;
import com.kafka.producer_1.repositories.ChatRoomRepository;
import com.kafka.producer_1.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
    private static final String ROOM_ID_ATTR = "roomId";

    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;
    private final ObjectMapper mapper;

    private final Map<UUID, Set<WebSocketSession>> rooms = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        UUID roomId;
        try {
            String path = Optional.ofNullable(session.getUri())
                    .map(URI::getPath)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid WebSocket URI"));
            roomId = UUID.fromString(path.substring(path.lastIndexOf('/') + 1));
        } catch (IllegalArgumentException e) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }
        if (!chatRoomRepository.existsById(roomId)) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }
        session.getAttributes().put(ROOM_ID_ATTR, roomId);
        rooms.compute(roomId, (key, value) -> {
            if (value == null) {
                value = ConcurrentHashMap.newKeySet();
            }
            value.add(session);
            return value;
        });
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        UUID roomId = (UUID) session.getAttributes().get(ROOM_ID_ATTR);

        ChatIn in;
        try {
            in = mapper.readValue(message.getPayload(), ChatIn.class);
        } catch (JacksonException e) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }

        ChatOut out;
        try {
            out = chatService.send(roomId, in.senderId(), in.content());
        } catch (ResponseStatusException e) {
            session.close(CloseStatus.POLICY_VIOLATION);
            return;
        }
        broadcast(roomId, out);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        UUID roomId = (UUID) session.getAttributes().get(ROOM_ID_ATTR);
        if (roomId != null) {
            rooms.computeIfPresent(roomId, (key, value) -> {
                value.remove(session);
                return value.isEmpty() ? null : value;
            });
        }
    }

    public void broadcast(UUID roomId, ChatOut dto) throws IOException {
        TextMessage out = new TextMessage(mapper.writeValueAsString(dto));
        for (WebSocketSession s : rooms.getOrDefault(roomId, Set.of())) {
            if (s.isOpen()) {
                synchronized (s) {
                    s.sendMessage(out);
                }
            }
        }
    }
}
