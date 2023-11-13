package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Image;
import com.app.travelplan.model.entity.Review;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReviewDto {
    private long id;
    private int point;
    private String content;
    private String[] imageUrl;
    private long placeId;
    private UserDto userDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewDto toDto(Review review) {
        List<Image> images = review.getImageList();
        String[] imageUrl = new String[images.size()];

        int index=0;
        for (Image i:
                images) {
            imageUrl[index]=i.getUrlImage();
            index++;
        }

        return ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .point(review.getPoint())
                .imageUrl(imageUrl)
                .placeId(review.getPlaces().getId())
                .userDto(UserDto.toDto(review.getUser()))
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
