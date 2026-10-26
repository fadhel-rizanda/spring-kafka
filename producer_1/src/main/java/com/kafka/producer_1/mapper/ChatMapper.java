package com.kafka.producer_1.mapper;

import com.kafka.producer_1.dtos.request.ChatIn;
import com.kafka.producer_1.dtos.response.ChatOut;
import com.kafka.producer_1.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.socket.TextMessage;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ChatMapper {
    @Mapping(target = "senderId", source = "payload")
    @Mapping(target = "content", source = "payload")
    ChatIn toChatIn(TextMessage message);

    @Mapping(target = "roomId", source = "chatRoom.id")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "senderName", source = "sender.username")
    ChatOut toChatOut(Message message);
}
