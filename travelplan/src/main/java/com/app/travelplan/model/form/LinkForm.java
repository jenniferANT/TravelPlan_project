package com.app.travelplan.model.form;

import com.app.travelplan.model.entity.Link;
import lombok.Data;

@Data
public class LinkForm {
    private String name;
    private String url;

    public LinkForm(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
