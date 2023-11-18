package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Link;
import com.app.travelplan.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LinkDto {
    private long id;
    private String name;
    private String url;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime updatedAt;

    public static LinkDto toDto(Link link) {
        return LinkDto.builder()
                .id(link.getId())
                .name(link.getName())
                .url(link.getUrl())
                .createdAt(link.getCreatedAt())
                .updatedAt(link.getUpdatedAt())
                .build();
    }
}
