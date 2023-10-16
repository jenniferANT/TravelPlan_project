package com.app.travelplan.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
@Builder
public class AuthDto {
    private String token;
    private String refreshToken;
    private String username;
    private String role;

    public static AuthDto from(Authentication auth, String token, String refreshToken){
        return AuthDto.builder()
                .username(auth.getName())
                .role(auth.getAuthorities().stream().findFirst().get().getAuthority())
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
