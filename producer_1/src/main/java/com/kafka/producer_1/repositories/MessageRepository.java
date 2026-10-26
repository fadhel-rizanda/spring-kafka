package com.kafka.producer_1.repositories;

import com.kafka.producer_1.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    Set<Message> findByChatRoomIdOrderByCreatedAtAsc(UUID chatRoomId);
}
