package com.app.travelplan.controller;

import com.app.travelplan.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;

    @PostMapping("/api/v1/cart")
    public ResponseEntity addFollow(@RequestParam("planId") long planId) {
        return new ResponseEntity<>(cartService.addFollow(planId), HttpStatus.OK);
    }

    @DeleteMapping("api/v1/cart")
    public ResponseEntity deleteFollow(@RequestParam("planId") long planId) {
        return new ResponseEntity<>(cartService.deleteFollow(planId), HttpStatus.OK);
    }
}
