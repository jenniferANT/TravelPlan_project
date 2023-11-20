package com.app.travelplan.controller;

import com.app.travelplan.model.form.VehicleForm;
import com.app.travelplan.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("api/v1/admin/vehicle")
    public ResponseEntity save(@RequestBody VehicleForm vehicleForm) {
        return new ResponseEntity<>(vehicleService.save(vehicleForm), HttpStatus.OK);
    }

    @GetMapping("api/v1/vehicle")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(vehicleService.getAll(), HttpStatus.OK);
    }
}
