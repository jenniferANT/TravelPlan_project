package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
@Builder
public class AuthDto {
    private String token;
    private String refreshToken;
    private String username;
    private String name;
    private String avatar;
    private String email;
    private String role;

    public static AuthDto from(User user, String token, String refreshToken){
        return AuthDto.builder()
                .username(user.getUsername())
                .role(user.getAuthorities().stream().findFirst().get().getAuthority())
                .token(token)
                .refreshToken(refreshToken)
                .name(user.getName())
                .avatar(user.getAvatar() != null ? user.getAvatar().getUrlImage() : "https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTmMSk4EUfCgAqlsjDNmFpgRYSfv7Ms_hubGzPvdLwpzNw3cS_fZX-IiLZHXwPCwTFc")
                .email(user.getEmail())
                .build();
    }
}
