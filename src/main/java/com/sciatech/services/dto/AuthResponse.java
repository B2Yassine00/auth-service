package com.sciatech.services.dto;

import com.sciatech.services.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private long id;
    private String token;
    private String username;
    private Role role;

}
