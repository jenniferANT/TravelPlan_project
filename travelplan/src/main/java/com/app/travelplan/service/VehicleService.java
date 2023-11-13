package com.app.travelplan.service;

import com.app.travelplan.model.dto.ReviewDto;
import com.app.travelplan.model.entity.Vehicle;
import com.app.travelplan.model.form.ReviewForm;
import com.app.travelplan.model.form.VehicleForm;

import java.util.List;

public interface VehicleService {
    Vehicle save(VehicleForm vehicleForm);
    List<Vehicle> getAll();
}
