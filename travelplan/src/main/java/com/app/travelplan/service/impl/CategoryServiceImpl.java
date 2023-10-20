package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.CategoryDto;
import com.app.travelplan.model.entity.Category;
import com.app.travelplan.model.form.CategoryForm;
import com.app.travelplan.repository.CategoryRepository;
import com.app.travelplan.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto save(CategoryForm categoryForm) {
        if (categoryRepository.existsByName(categoryForm.getName())) {
            throw new IllegalArgumentException("Category exists with name " + categoryForm.getName());
        }
        if(categoryForm.getCatagoryId() !=null) {
            Category categoryParent = categoryRepository.findById(categoryForm.getCatagoryId())
                    .orElseThrow(() ->
                            new NotFoundException("Category not found with id " + categoryForm.getCatagoryId()));

            Category category = Category.builder()
                    .name(categoryForm.getName())
                    .category(categoryParent)
                    .build();
            categoryParent.getCategories().add(category);

            return CategoryDto.toDto(categoryRepository.save(category));
        } else {
            return CategoryDto.toDto(categoryRepository.save(
                    Category.builder()
                            .name(categoryForm.getName())
                            .category(null)
                            .build()
            ));
        }
    }

    @Override
    public CategoryDto getByName(String name) {
        return CategoryDto.toDto(categoryRepository.findByName(name)
                .orElseThrow(() ->
                        new NotFoundException("Name not found with name " + name)));
    }

    @Override
    public CategoryDto getById(long id) {
        return CategoryDto.toDto(categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Name not found with id " + id)));
    }

    @Override
    public void deleteById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Category not found"));
        delete(category);
    }

    @Override
    public void deleteByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() ->
                        new NotFoundException("Name not found with name " + name));
        delete(category);
    }

    private void delete(Category category) {
        if(category.getCategories().isEmpty()) {
            Category categoryParent = category.getCategory();
            categoryParent.getCategories().remove(category);
            category.setCategory(null);
            categoryRepository.save(categoryParent);
            categoryRepository.delete(category);
            return;
        }
        throw new IllegalArgumentException("Category has child");
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllByCategoryId(long id) {
        return categoryRepository.findAllByCategory_Id(id)
                .stream()
                .map(CategoryDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllByCategoryName(String name) {
        return categoryRepository.findAllByCategory_Name(name)
                .stream()
                .map(CategoryDto::toDto)
                .collect(Collectors.toList());
    }
}
