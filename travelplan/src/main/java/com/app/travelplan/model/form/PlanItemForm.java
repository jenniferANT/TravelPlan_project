package com.app.travelplan.model.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanItemForm {
    private LocalDateTime startTime;
    private String note;
    private long placesId;
}
