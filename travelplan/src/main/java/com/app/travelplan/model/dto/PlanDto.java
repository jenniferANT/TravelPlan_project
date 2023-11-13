package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PlanDto {
    private Long id;
    private String title;
    private AddressDto location;
    private String destination;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private int numberPeople;

    private long distance; //khoảng cách từ người này đến địa điểm du lịch
    private long estimatedTotalDistance; //Tổng quang dduong ước tính
    private int numberVehicle; //số phương tiện
    private BigDecimal expense; //'tổng chi phi tren 1 ng'
    private BigDecimal costVehicle; //tổng chi phí phương tiện / 1ng
    private BigDecimal costEat; //tổng chi phí ăn uống /1ng
    private BigDecimal costPlay; //tổng chi phí chơi /1ng
    private List<PlanItem> planItems;
    private UserDto user;
    private List<CategoryDto> categories;
    private Vehicle vehicle;

    public static PlanDto toDto(Plan plan) {
        Collections.sort(plan.getPlanItems(), Comparator.comparing(PlanItem::getStartTime));
        return PlanDto.builder()
                .id(plan.getId())
                .title(plan.getTitle())
                .location(AddressDto.toDto(plan.getLocation()))
                .destination(plan.getDestination())
                .beginDate(plan.getBeginDate())
                .endDate(plan.getEndDate())
                .numberPeople(plan.getNumberPeople())
                .distance(plan.getDistance())
                .estimatedTotalDistance(plan.getEstimatedTotalDistance())
                .numberVehicle(plan.getNumberVehicle())
                .expense(plan.getExpense())
                .costVehicle(plan.getCostVehicle())
                .costEat(plan.getCostEat())
                .costPlay(plan.getCostPlay())
                .planItems(
                        plan.getPlanItems()
                )
                .user(UserDto.toDto(plan.getUser()))
                .categories(plan.getCategories()
                        .stream()
                        .map(CategoryDto::toDto)
                        .collect(Collectors.toList()))
                .vehicle(plan.getVehicle())
                .build();
    }
}
