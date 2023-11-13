package com.app.travelplan.service;

import com.app.travelplan.model.entity.PlanItem;
import com.app.travelplan.model.form.PlanItemForm;

public interface PlanItemService {

    PlanItem update(long planItemId, PlanItemForm planItemForm);
}
