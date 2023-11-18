package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryForm {
    private Long catagoryId; // danh mục này thuộc danh mục nào
    @NotBlank
    private String name;
    private String imageCategory; // optional
}
