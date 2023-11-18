package com.app.travelplan.controller;

import com.app.travelplan.model.form.LinkForm;
import com.app.travelplan.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/api/v1/links")
    public ResponseEntity save(@RequestBody LinkForm linkForm) {
        return new ResponseEntity(linkService.save(linkForm), HttpStatus.OK);
    }

    @GetMapping("/api/v1/links/get-all")
    public ResponseEntity getAll() {
        return new ResponseEntity(linkService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/links/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(linkService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/api/v1/links/{id}")
    public ResponseEntity update(@RequestBody LinkForm linkForm,@PathVariable("id") long id) {
        return new ResponseEntity(linkService.update(linkForm, id), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/links")
    public ResponseEntity delete(@RequestParam("id") long id) {
        linkService.deleteById(id);
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
