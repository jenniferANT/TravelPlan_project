package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class VehicleForm {
    @NotBlank
    private int seats; //ghế ngồi
    @NotBlank
    private String name;
    @NotBlank
    private BigDecimal cost; //chi phí xăng/dầu trên 1 km
    @NotBlank
    private long averageSpeed; //tốc độ trung bình
}
