package com.app.travelplan.service.impl;

import com.app.travelplan.model.entity.Places;
import com.app.travelplan.model.entity.PlanItem;
import com.app.travelplan.model.form.PlanItemForm;
import com.app.travelplan.repository.PlacesRepository;
import com.app.travelplan.repository.PlanItemRepository;
import com.app.travelplan.service.PlanItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class PlanItemServiceImpl implements PlanItemService {
    private final PlanItemRepository planItemRepository;
    private final PlacesRepository placesRepository;

    @Override
    public PlanItem update(long planItemId, PlanItemForm planItemForm) {

        PlanItem planItem = planItemRepository.findById(planItemId)
                .orElseThrow(() ->
                        new NotFoundException("Plan not found with id "+planItemId));

        Places places = placesRepository.findById(planItemForm.getPlacesId())
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id "+planItemForm.getPlacesId()));

        planItem.setImageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE");
        planItem.setTitle(places.getTitle());
        planItem.setContent(places.getDescription());
        planItem.setMoney(places.getCost());
        planItem.setNote(planItemForm.getNote());
        planItem.setStartTime(planItemForm.getStartTime());
        return planItemRepository.save(planItem);
    }
}
