package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private long id;
    private String username;
    private String role;
    private String name;
    private String avatar; // image
    private String email;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime updatedAt;

    public static UserDto toDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().getName())
                .name(user.getName())
                .avatar(user.getAvatar() == null
                        ? "https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcTmMSk4EUfCgAqlsjDNmFpgRYSfv7Ms_hubGzPvdLwpzNw3cS_fZX-IiLZHXwPCwTFc"
                        : user.getAvatar().getUrlImage())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
