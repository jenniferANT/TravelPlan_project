package com.app.travelplan.controller;

import com.app.travelplan.model.form.ReviewForm;
import com.app.travelplan.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("api/v1/reviews")
    public ResponseEntity save(@RequestBody ReviewForm reviewForm) {
        return new ResponseEntity<>(reviewService.save(reviewForm), HttpStatus.OK);
    }

    @GetMapping("api/v1/reviews")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(reviewService.getAll(), HttpStatus.OK);
    }

    @GetMapping("api/v1/reviews/{id}/places")
    public ResponseEntity getAllByPlaceId(@PathVariable("id") long placeId) {
        return new ResponseEntity<>(reviewService.getAllByPlaceId(placeId), HttpStatus.OK);
    }

    @DeleteMapping("api/v1/reviews")
    public ResponseEntity deleteById(@RequestParam("id") long id) {
        reviewService.deleteById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("api/v1/reviews/{id}")
    public ResponseEntity update(@RequestBody ReviewForm reviewForm, @PathVariable long id) {
        return new ResponseEntity<>(reviewService.update(reviewForm, id), HttpStatus.OK);
    }

    @GetMapping("api/v1/reviews/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        return new ResponseEntity<>(reviewService.getById(id), HttpStatus.OK);
    }
}
