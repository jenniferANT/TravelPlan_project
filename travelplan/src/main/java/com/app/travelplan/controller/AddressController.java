package com.app.travelplan.controller;

import com.app.travelplan.model.form.AddressForm;
import com.app.travelplan.service.AddressService;
import com.app.travelplan.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/api/v1/address")
    public ResponseEntity save(@RequestBody AddressForm addressForm) {
        return new ResponseEntity(addressService.save(addressForm), HttpStatus.OK);
    }

    @GetMapping("/api/v1/address/get-all")
    public ResponseEntity getAll() {
        return new ResponseEntity(addressService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/address/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(addressService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/api/v1/address/{id}")
    public ResponseEntity update(@RequestBody AddressForm addressForm,@PathVariable("id") long id) {
        return new ResponseEntity(addressService.update(addressForm, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/address")
    public ResponseEntity delete(@RequestParam("id") long id) {
        addressService.deleteById(id);
        return new ResponseEntity("Success", HttpStatus.OK);
    }


    public ResponseEntity setLongLa() {
        addressService.setLongLa();
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
