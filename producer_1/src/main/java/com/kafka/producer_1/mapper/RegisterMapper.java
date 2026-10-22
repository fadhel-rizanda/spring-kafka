package com.kafka.producer_1.mapper;

import com.kafka.producer_1.dtos.request.RegisterRequest;
import com.kafka.producer_1.dtos.response.RegisterResponse;
import com.kafka.producer_1.models.Role;
import com.kafka.producer_1.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RegisterMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(RegisterRequest request);

    @Mapping(target = "token", expression = "java(token)")
    @Mapping(target = "roles", expression = "java(toRoleSet(user))")
    RegisterResponse toRegisterResponse(User user, String token);

    default Set<String> toRoleSet(User user) {
        return user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }
}
