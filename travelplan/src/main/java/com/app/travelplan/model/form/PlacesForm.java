package com.app.travelplan.model.form;

import com.app.travelplan.model.entity.Places;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlacesForm {
    private String title;
    private String phoneNumber;
    private BigDecimal cost;
    private boolean isFull; //có phải mở 24h không
    @JsonFormat(pattern = "HH:mm")
    private LocalTime beginDay; //giờ mở cửa
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endDay; //giờ đóng cửa

    private List<Long> categoryId = new ArrayList<>();
    private List<Long> imageId = new ArrayList<>();

    private long linkId;
    private long addressId;
}
