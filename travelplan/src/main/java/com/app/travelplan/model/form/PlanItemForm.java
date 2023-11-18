package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PlanItemForm {
    @NotBlank
    private LocalDateTime startTime;
    @NotNull
    private String note;
    @NotBlank
    private long placesId;
}
