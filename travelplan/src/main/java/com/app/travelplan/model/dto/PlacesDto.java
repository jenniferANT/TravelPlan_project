package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Image;
import com.app.travelplan.model.entity.Places;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PlacesDto {
    private long id;
    private String title;
    private String description;
    private String phoneNumber;
    private float point;
    private String[] imageUrl;
    private BigDecimal cost;
    private long count; //số ngừoi đánh giá
    private List<CategoryDto> category;
    private AddressDto addressDto;
    private LinkDto link;
    private UserDto user;
    private boolean isFull;

    private long minTimePlaces; //thời gian trung bình dành cho địa điểm này
    private long maxTimePlaces; //thời gian trung bình dành cho địa điểm này

    private LocalTime beginDay;
    private LocalTime endDay;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PlacesDto toDto(Places places) {
        List<Image> images = places.getImages();
        String[] imageUrl = new String[images.size()];

        int index=0;
        for (Image i:
             images) {
            imageUrl[index]=i.getUrlImage();
            index++;
        }
        return PlacesDto.builder()
                .id(places.getId())
                .title(places.getTitle())
                .description(places.getDescription())
                .phoneNumber(places.getPhoneNumber())
                .point(places.getPoint())
                .imageUrl(imageUrl)
                .cost(places.getCost())
                .minTimePlaces(places.getMinTimePlaces())
                .maxTimePlaces(places.getMaxTimePlaces())
                .count(places.getReviews()!=null ? places.getReviews().size() : 0)
                .addressDto(AddressDto.toDto(places.getAddress()))
                .category(places.getCategories()
                        .stream()
                        .map(CategoryDto::toDto)
                        .collect(Collectors.toList()))
                .link(LinkDto.toDto(places.getLink()))
                .user(UserDto.toDto(places.getUser()))
                .isFull(places.isFull())
                .beginDay(places.getBeginDay())
                .endDay(places.getEndDay())
                .createdAt(places.getCreatedAt())
                .updatedAt(places.getUpdatedAt())
                .build();
    }
}
