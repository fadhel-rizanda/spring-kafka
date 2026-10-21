package com.kafka.producer_1.models;

import com.kafka.producer_1.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mt_order")
@Getter
@Setter
public class Order extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private GeneralStatus status = GeneralStatus.PENDING;

    private Long amount;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /*@OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true // pada saat salah satu index list di remove maka dia otomatis delete, sedangkan kalo cascadetype remove maka dia cuman diset null
    )*/
    /*@ManyToMany
    @JoinTable(
            name = "tr_order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;*/
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts;
}
