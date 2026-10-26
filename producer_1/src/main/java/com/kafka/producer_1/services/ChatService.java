package com.kafka.producer_1.services;

import com.kafka.producer_1.dtos.response.ChatOut;
import com.kafka.producer_1.mapper.ChatMapper;
import com.kafka.producer_1.models.Message;
import com.kafka.producer_1.repositories.ChatRoomRepository;
import com.kafka.producer_1.repositories.MessageRepository;
import com.kafka.producer_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;

    public ChatOut send(UUID roomId, UUID senderId, String content) {
        if (!chatRoomRepository.existsByIdAndUsersId(roomId, senderId)) {
            throw new IllegalArgumentException("User is not part of the chat room");
        }
        Message message = new Message();
        message.setChatRoom(chatRoomRepository.getReferenceById(roomId));
        message.setSender(userRepository.getReferenceById(senderId));
        message.setContent(content);
        return chatMapper.toChatOut(messageRepository.save(message));
    }

    public List<ChatOut> history(UUID roomId) {
        return messageRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId).stream()
                .map(chatMapper::toChatOut)
                .toList();
    }
}
