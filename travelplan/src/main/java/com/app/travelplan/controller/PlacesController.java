package com.app.travelplan.controller;

import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.form.PlacesForm;
import com.app.travelplan.service.PlacesService;
import com.app.travelplan.utils.PageableConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlacesController {
    private final PlacesService placesService;

    @PostMapping("/api/v1/places")
    public ResponseEntity save(@RequestBody PlacesForm placesForm) {
        return new ResponseEntity(placesService.save(placesForm), HttpStatus.OK);
    }

    @GetMapping("/api/v1/places/get-all")
    public ResponseEntity getAll(
            @RequestParam(value = "pageNo", defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER , required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstants.DEFAULT_PAGE_SIZE , required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION , required = false) String sortDir
    ) {
        return new ResponseEntity(placesService.getAll(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/api/v1/places/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(placesService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/api/v1/places/{id}")
    public ResponseEntity update(@RequestBody PlacesForm placesForm,@PathVariable("id") long id) {
        return new ResponseEntity(placesService.update(placesForm, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/places")
    public ResponseEntity delete(@RequestParam("id") long id) {
        placesService.deleteById(id);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @GetMapping("/api/v1/places/my-follow")
    public ResponseEntity getMyFollow() {
        return new ResponseEntity(placesService.getMyFollow(), HttpStatus.OK);
    }

    @PatchMapping("/api/v1/places/{id}/add-category")
    public ResponseEntity addCategoryToPlace(@PathVariable("id") long placeId, @RequestParam(value = "categories[]") long[] ids) {
        return new ResponseEntity(placesService.addCategoryToPlace(placeId, ids), HttpStatus.OK);
    }

    @PatchMapping("/api/v1/places/{id}/remove-category")
    public ResponseEntity delCategoryToPlace(@PathVariable("id") long placeId, @RequestParam(value = "categories[]") long[] ids) {
        return new ResponseEntity(placesService.delCategoryToPlace(placeId, ids), HttpStatus.OK);
    }
}
