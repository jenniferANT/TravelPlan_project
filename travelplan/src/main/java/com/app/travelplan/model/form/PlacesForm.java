package com.app.travelplan.model.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlacesForm {
    @NotBlank
    private String title;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal cost;
    @NotBlank
    private boolean isFull; //có phải mở 24h không
    @JsonFormat(pattern = "HH:mm")
    private LocalTime beginDay; //giờ mở cửa
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endDay; //giờ đóng cửa

    @NotBlank
    private long minTimePlaces; //thời gian trung bình dành cho địa điểm này
    @NotBlank
    private long maxTimePlaces; //thời gian trung bình dành cho địa điểm này

    private List<Long> categoryId = new ArrayList<>();
    private List<Long> imageId = new ArrayList<>();

    @NotBlank
    private long linkId;
    @NotBlank
    private long addressId;
}
