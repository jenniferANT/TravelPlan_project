package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String imageCategory;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime updatedAt;

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .imageCategory(category.getImageCategory() != null ? category.getImageCategory() : "")
                .categoryId(category.getCategory()!=null?category.getCategory().getId():null)
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
