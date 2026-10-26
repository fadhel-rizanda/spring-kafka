package com.kafka.producer_1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "db_user")
@Getter
@Setter
public class User extends BaseEntity {
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
        name = "tr_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany(mappedBy = "users")
    private Set<ChatRoom> chatRooms;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private Set<Message> messages;
}
