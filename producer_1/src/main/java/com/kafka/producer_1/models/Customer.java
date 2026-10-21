package com.kafka.producer_1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mt_customer")
@Getter
@Setter
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
