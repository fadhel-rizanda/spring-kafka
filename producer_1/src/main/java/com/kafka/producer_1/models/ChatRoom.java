package com.kafka.producer_1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "db_chat_room")
public class ChatRoom extends BaseEntity {
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tr_chat_room_user",
            joinColumns = @JoinColumn(name = "chat_room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ChatRoom> chatRooms;
}
