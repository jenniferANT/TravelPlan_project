package com.app.travelplan.service;

import com.app.travelplan.model.entity.Category;
import com.app.travelplan.model.entity.Image;

import java.util.List;

public interface GeneralService {
    List<Category> getAllCategoryByArrayId(List<Long> categories);

    List<Image> getAllImageByArrayId(List<Long> images);
}
