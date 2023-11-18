package com.app.travelplan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address location;
    private String destination;
    @Column(name = "begin_date")
    private LocalDateTime beginDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "number_people")
    private int numberPeople;

    private long distance; //khoảng cách từ người này đến địa điểm du lịch
    @Column(name = "estimated_total_distance")
    private long estimatedTotalDistance; //Tổng quang dduong ước tính

    @Column(name = "number_vehicle")
    private int numberVehicle; //số phương tiện
    private BigDecimal expense; //'tổng chi phi tren 1 ng'
    @Column(name = "cost_vehicle")
    private BigDecimal costVehicle; //tổng chi phí phương tiện / 1ng
    @Column(name = "cost_eat")
    private BigDecimal costEat; //tổng chi phí ăn uống /1ng
    @Column(name = "cost_play")
    private BigDecimal costPlay; //tổng chi phí chơi /1ng

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PlanItem> planItems;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plan_cart",
            joinColumns = @JoinColumn(name = "plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id")
    )
    private List<Cart> carts;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plan_category",
            joinColumns = @JoinColumn(name = "plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Share> shares;
}
