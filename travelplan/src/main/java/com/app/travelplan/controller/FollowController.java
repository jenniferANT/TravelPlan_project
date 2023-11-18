package com.app.travelplan.controller;

import com.app.travelplan.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/api/v1/follow")
    public ResponseEntity addFollow(@RequestParam("placeId") long placeId) {
        return new ResponseEntity<>(followService.addFollow(placeId), HttpStatus.OK);
    }

    @DeleteMapping("api/v1/follow")
    public ResponseEntity deleteFollow(@RequestParam("placeId") long placeId) {
        return new ResponseEntity<>(followService.deleteFollow(placeId), HttpStatus.OK);
    }
}
