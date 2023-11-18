package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewForm {
    @NotBlank
    private int point;
    @NotBlank
    private String content;
    private List<Long> imageId = new ArrayList<>();
    @NotBlank
    private long placeId;
}
