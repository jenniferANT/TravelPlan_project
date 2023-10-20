package com.app.travelplan.controller;

import com.app.travelplan.model.dto.CategoryDto;
import com.app.travelplan.model.form.CategoryForm;
import com.app.travelplan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/api/v1/admin/category/")
    public ResponseEntity save(CategoryForm categoryForm) {
        return new ResponseEntity(categoryService.save(categoryForm), HttpStatus.OK);
    }

    @GetMapping("/api/v1/category/name")
    public ResponseEntity getByName(@RequestParam("name") String name) {
        return new ResponseEntity(categoryService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/api/v1/category/id")
    public ResponseEntity getById(@RequestParam("id") long id) {
        return new ResponseEntity(categoryService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/admin/category/id")
    public ResponseEntity deleteById(@RequestParam("id") long id) {
        categoryService.deleteById(id);
        return new ResponseEntity("Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/admin/category/name")
    public ResponseEntity deleteByName(@RequestParam("name") String name) {
        categoryService.deleteByName(name);
        return new ResponseEntity("Successfully", HttpStatus.OK);
    }

    @GetMapping("/api/v1/category/get-all")
    public ResponseEntity getAll() {
        return new ResponseEntity(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/category/category-child/id")
    public ResponseEntity getAllByCategoryId(@RequestParam("id") long id) {
        return new ResponseEntity(categoryService.getAllByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/api/v1/category/category-child/name")
    public ResponseEntity getAllByCategoryName(@RequestParam("name") String name) {
        return new ResponseEntity(categoryService.getAllByCategoryName(name), HttpStatus.OK);
    }
}
