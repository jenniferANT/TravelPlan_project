package com.app.travelplan.service;

import com.app.travelplan.model.dto.CategoryDto;
import com.app.travelplan.model.form.CategoryForm;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryForm categoryForm);
    CategoryDto getByName(String name);
    CategoryDto getById(long id);
    void deleteById(long id);
    void deleteByName(String name);
    List<CategoryDto> getAll();
    List<CategoryDto> getAllByCategoryId(long id);
    List<CategoryDto> getAllByCategoryName(String name);
}
