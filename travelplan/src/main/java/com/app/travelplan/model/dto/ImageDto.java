package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Image;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ImageDto {
    private long imageId;
    private String imageUrl;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime updatedAt;

    public static ImageDto toDto(Image image) {
        return ImageDto.builder()
                .imageId(image.getId())
                .imageUrl(image.getUrlImage())
                .createdAt(image.getCreatedAt())
                .updatedAt(image.getUpdatedAt())
                .build();
    }
}
