package com.app.travelplan.controller;

import com.app.travelplan.model.form.PlanItemForm;
import com.app.travelplan.service.PlanItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlanItemController {
    private final PlanItemService planItemService;

    @PutMapping("api/v1/plan-item/{id}")
    public ResponseEntity update(@PathVariable("id") long planItemId, @RequestBody PlanItemForm planItemForm) {
        return new ResponseEntity<>(planItemService.update(planItemId, planItemForm), HttpStatus.OK);
    }
}
