package com.app.travelplan.service;

import com.app.travelplan.model.dto.AddressDto;
import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.form.AddressForm;
import com.app.travelplan.model.form.PlacesForm;

import java.util.List;

public interface AddressService {
    AddressDto save(AddressForm addressForm);
    List<AddressDto> getAll();
    void deleteById(long id);
    AddressDto update(AddressForm addressForm, long id);
    AddressDto getById(long id);

    void setLongLa();
}
