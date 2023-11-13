package com.app.travelplan.service;

import com.app.travelplan.model.dto.ImageDto;
import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.dto.ReviewDto;
import com.app.travelplan.model.form.PlacesForm;
import com.app.travelplan.model.form.ReviewForm;

import java.util.List;

public interface ReviewService {
    ReviewDto save(ReviewForm reviewForm);
    List<ReviewDto> getAll();
    List<ReviewDto> getAllByPlaceId(long placeId);
    void deleteById(long id);
    ReviewDto update(ReviewForm reviewForm, long id);
    ReviewDto getById(long id);
}
