package com.app.travelplan.model.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewForm {
    private int point;
    private String content;
    private List<Long> imageId = new ArrayList<>();
    private long placeId;
}
