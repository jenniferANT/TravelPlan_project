package com.app.travelplan.model.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VehicleForm {
    private int seats; //ghế ngồi
    private String name;
    private BigDecimal cost; //chi phí xăng/dầu trên 1 km
    private long averageSpeed; //tốc độ trung bình
}
