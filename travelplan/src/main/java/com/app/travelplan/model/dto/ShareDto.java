package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Share;
import com.app.travelplan.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ShareDto {
    private Long id;
    private String message;
    private UserDto remitter;
    private List<UserDto> receiver;
    private long planId;

    public static ShareDto toDto(Share share) {
        return ShareDto.builder()
                .id(share.getId())
                .message(share.getMessage())
                .remitter(UserDto.toDto(
                        share.getRemitter()
                ))
                .receiver(share.getReceiver()
                        .stream()
                        .map(UserDto::toDto)
                        .collect(Collectors.toList()))
                .planId(share.getPlan().getId())
                .build();
    }
}
