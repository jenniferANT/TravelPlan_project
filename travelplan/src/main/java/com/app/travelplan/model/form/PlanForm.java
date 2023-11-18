package com.app.travelplan.model.form;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlanForm {
    private long locationLatitude;
    private long locationLongitude;

    private String destination; //tên địa điểm tới
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private int numberPeople;
    private BigDecimal expense; //'tổng chi phi tren 1 ng'

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