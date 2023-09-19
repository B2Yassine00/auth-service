package com.sciatech.services.service;

import com.sciatech.services.configs.JWTService;
import com.sciatech.services.dto.AuthRequest;
import com.sciatech.services.dto.AuthResponse;
import com.sciatech.services.dto.RegisterRequest;
import com.sciatech.services.models.Role;
import com.sciatech.services.models.User;
import com.sciatech.services.models.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signin(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .role(request.getRole())
                .enabled(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User utilisateur=repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole())
                .id(utilisateur.getId())
                .build();
    }

    public AuthResponse authenticate( AuthRequest userData) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(),userData.getPassword()));
        if (!repository.existsUserByUsername(userData.getUsername())){
            {
                throw new UsernameNotFoundException("User by username " + userData.getUsername() + " was not found");
            }
        }
        var user = repository.findByUsername(userData.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse
                .builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole())
                .id(user.getId())
                .build();
    }
}
