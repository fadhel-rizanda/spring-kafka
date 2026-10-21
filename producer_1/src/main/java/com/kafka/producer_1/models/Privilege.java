package com.kafka.producer_1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "db_privilege")
public class Privilege extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;
}
