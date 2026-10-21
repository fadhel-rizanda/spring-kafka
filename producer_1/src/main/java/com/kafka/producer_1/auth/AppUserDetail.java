package com.kafka.producer_1.auth;

import com.kafka.producer_1.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public class AppUserDetail implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .flatMap(role -> Stream.concat(
                        role.getPrivileges().stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())),
                        Stream.of(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()))
                )).collect(Collectors.toSet());
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
