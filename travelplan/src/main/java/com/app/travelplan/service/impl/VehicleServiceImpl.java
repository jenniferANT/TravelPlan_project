package com.app.travelplan.service.impl;

import com.app.travelplan.model.entity.Vehicle;
import com.app.travelplan.model.form.VehicleForm;
import com.app.travelplan.repository.VehicleRepository;
import com.app.travelplan.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(VehicleForm vehicleForm) {
        Vehicle vehicle = Vehicle.builder()
                .seats(vehicleForm.getSeats())
                .name(vehicleForm.getName())
                .cost(vehicleForm.getCost())
                .averageSpeed(vehicleForm.getAverageSpeed())
                .build();
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }
}
