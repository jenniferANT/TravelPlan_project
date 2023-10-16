package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Image;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {
    private long imageId;
    private String imageUrl;

    public static ImageDto toDto(Image image) {
        return ImageDto.builder()
                .imageId(image.getId())
                .imageUrl(image.getUrlImage())
                .build();
    }
}
