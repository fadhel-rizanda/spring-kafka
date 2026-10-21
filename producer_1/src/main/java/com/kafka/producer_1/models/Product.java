package com.kafka.producer_1.models;

import com.kafka.producer_1.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mt_product")
@Getter
@Setter
public class Product extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private GeneralStatus status = GeneralStatus.PENDING;
    private Long amount;
    private Double price;

//    @ManyToMany(mappedBy = "products")
//    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts;
}
