package com.app.travelplan.service.impl;

import com.app.travelplan.model.entity.Category;
import com.app.travelplan.model.entity.Image;
import com.app.travelplan.repository.CategoryRepository;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<Category> getAllCategoryByArrayId(List<Long> temp) {
        List<Category> categories = new ArrayList<Category>();
        if(temp.isEmpty()) return categories;

        for (long i : temp) {
            categories.add(
                    categoryRepository.findById(i)
                            .orElseThrow(()->
                                    new NotFoundException("Category not fount with id "+i))
            );
        }
        return categories;
    }

    @Override
    public List<Image> getAllImageByArrayId(List<Long> temp) {
        List<Image> images = new ArrayList<Image>();
        for (long i : temp) {
            images.add(
                    imageRepository.findById(i)
                            .orElseThrow(()->
                                    new NotFoundException("Image not fount with id "+i))
            );
        }
        return images;
    }
}
