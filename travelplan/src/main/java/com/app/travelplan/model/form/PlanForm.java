package com.app.travelplan.model.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlanForm {
    @NotBlank
    private long locationLatitude;
    @NotBlank
    private long locationLongitude;

    @NotBlank
    private String destination; //tên địa điểm tới

    @JsonFormat(pattern = "HH:mm yyyy-MM-dd")
    @NotBlank
    private LocalDateTime beginDate;
    @JsonFormat(pattern = "HH:mm yyyy-MM-dd")
    @NotBlank
    private LocalDateTime endDate;

    @NotBlank
    private int numberPeople;
    @NotBlank
    private BigDecimal expense; //'tổng chi phi tren 1 ng'

    @NotBlank
    private long vehicleId;
    private List<Long> categoryId = new ArrayList<>();
}

/*
{
  "locationLatitude": 0,
  "locationLongitude": 0,
  "destination": "Vũng Tàu",
  "beginDate": "05:00 20-11-2023",
  "endDate": "17:00 21-11-2023",
  "numberPeople": 4,
  "expense": 500000,
  "vehicleId": 1,
  "categoryId": [
    13, 19, 21
  ]
}
 */