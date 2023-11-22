package com.app.travelplan.service;

import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.dto.ListResponse;
import com.app.travelplan.model.form.PlacesForm;

import java.util.List;

public interface PlacesService {
    PlacesDto save(PlacesForm placesForm);
    ListResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteById(long id);
    PlacesDto update(PlacesForm placesForm, long id);
    PlacesDto getById(long id);
    List<PlacesDto> getMyFollow();

    PlacesDto addCategoryToPlace(long placeId, long[] ids);

    PlacesDto delCategoryToPlace(long placeId, long[] ids);

    PlacesDto addImageToPlace(long placeId, long[] ids);

    PlacesDto delImageToPlace(long placeId, long[] ids);
}
