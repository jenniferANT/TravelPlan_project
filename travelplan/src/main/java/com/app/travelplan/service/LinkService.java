package com.app.travelplan.service;

import com.app.travelplan.model.dto.LinkDto;
import com.app.travelplan.model.form.LinkForm;
import com.app.travelplan.model.form.PlacesForm;

import java.util.List;

public interface LinkService {
    LinkDto save(LinkForm linkForm);
    List<LinkDto> getAll();
    void deleteById(long id);
    LinkDto update(LinkForm linkForm, long id);
    LinkDto getById(long id);
}
