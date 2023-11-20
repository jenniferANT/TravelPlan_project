package com.app.travelplan.controller;

import com.app.travelplan.model.form.PlanForm;
import com.app.travelplan.service.PlanService;
import com.app.travelplan.utils.PageableConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlanController {
    private final PlanService planService;

    @PostMapping("api/v1/plan")
    public ResponseEntity generate(@RequestBody PlanForm planForm) {
        return new ResponseEntity<>(planService.generatePlan(planForm), HttpStatus.OK);
    }

    @GetMapping("/api/v1/plan/get-all")
    public ResponseEntity getAll(
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER , required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE , required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION , required = false) String sortDir
    ) {
        return new ResponseEntity(planService.getAll(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/api/v1/plan/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(planService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/api/v1/plan/my-history")
    public ResponseEntity getMyHistory() {
        return new ResponseEntity(planService.getAllHistory(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/plan/my-cart")
    public ResponseEntity getMyCart() {
        return new ResponseEntity(planService.getAllCart(), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/plan")
    public ResponseEntity deleteById(@RequestParam long id) {
        return new ResponseEntity<>(planService.delete(id), HttpStatus.OK);
    }
}
