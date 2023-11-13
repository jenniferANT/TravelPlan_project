package com.app.travelplan.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int seats; //ghế ngồi
    private String name;
    private BigDecimal cost; //chi phí xăng/dầu trên 1 km
    private long averageSpeed; //tốc độ trung bình
    public BigDecimal getPriceByDistance(double distance) { //giá tiền theo khoảng cách và giá thuê
        return this.cost.multiply(BigDecimal.valueOf(distance));
    }
}
