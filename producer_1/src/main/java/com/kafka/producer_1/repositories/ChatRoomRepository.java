package com.kafka.producer_1.repositories;

import com.kafka.producer_1.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    boolean existsByIdAndUsersId(UUID roomId, UUID userId);
}
