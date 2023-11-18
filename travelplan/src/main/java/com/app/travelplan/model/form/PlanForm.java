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

    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    @NotBlank
    private LocalDateTime beginDate;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
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
  "beginDate": "2023-11-18T05:00:52.548Z",
  "endDate": "2023-11-18T16:00:52.548Z",
  "numberPeople": 4,
  "expense": 500000,
  "vehicleId": 1,
  "categoryId": [
    13, 19, 21
  ]
}
 */