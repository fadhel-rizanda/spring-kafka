package com.kafka.producer_1.repositories;

import com.kafka.producer_1.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"roles", "roles.privileges"})
    Optional<User> findByEmail(String email);
}
