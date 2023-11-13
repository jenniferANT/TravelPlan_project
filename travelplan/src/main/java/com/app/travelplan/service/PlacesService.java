package com.app.travelplan.service;

import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.form.PlacesForm;

import java.util.List;

public interface PlacesService {
    PlacesDto save(PlacesForm placesForm);
    List<PlacesDto> getAll();
    void deleteById(long id);
    PlacesDto update(PlacesForm placesForm, long id);
    PlacesDto getById(long id);
    List<PlacesDto> getMyFollow();
}
