package com.kafka.producer_1.services;

import com.kafka.producer_1.auth.AppUserDetail;
import com.kafka.producer_1.auth.JWTService;
import com.kafka.producer_1.dtos.request.RegisterRequest;
import com.kafka.producer_1.dtos.response.RegisterResponse;
import com.kafka.producer_1.exception.NotFoundException;
import com.kafka.producer_1.mapper.RegisterMapper;
import com.kafka.producer_1.models.Role;
import com.kafka.producer_1.models.User;
import com.kafka.producer_1.repositories.RoleRepository;
import com.kafka.producer_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RegisterMapper registerMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse registerUser(RegisterRequest request) {
        User user = registerMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new NotFoundException("Role not found"));
        user.setRoles(Set.of(role));

        user = userRepository.save(user);

        String token = jwtService.generateJWT(new AppUserDetail(user));

        return registerMapper.toRegisterResponse(user, token);
    }

    public RegisterResponse loginUser(RegisterRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        AppUserDetail userDetail = (AppUserDetail) authentication.getPrincipal();
        String token = jwtService.generateJWT(userDetail);
        return registerMapper.toRegisterResponse(userDetail.getUser(), token);
    }

    public String logout(String token) {
        return "Logged out successfully";
    }
}
