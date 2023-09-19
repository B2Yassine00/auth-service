package com.sciatech.services.controller.auth;

import com.sciatech.services.dto.AuthRequest;
import com.sciatech.services.dto.AuthResponse;
import com.sciatech.services.dto.RegisterRequest;
import com.sciatech.services.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signin(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.signin(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
