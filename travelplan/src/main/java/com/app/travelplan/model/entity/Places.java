package com.app.travelplan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "places")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Places extends BaseEntity {  //chọn giờ mở cửa
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String phoneNumber;
    @Column(length = 2048)
    private String description;
    private float point;
    private BigDecimal cost;
    @Column(name = "is_full", nullable = true)
    private boolean isFull; //có phải mở 24h không
    @Column(name = "begin_day", nullable = true)
    private LocalTime beginDay; //giờ mở cửa
    @Column(name = "end_day", nullable = true)
    private LocalTime endDay; //giờ đóng cửa

    @Column(name = "min_time_places")
    private long minTimePlaces; //thời gian trung bình dành cho địa điểm này
    @Column(name = "max_time_places")
    private long maxTimePlaces; //thời gian trung bình dành cho địa điểm này

    @Transient
    private int value; //dùng cho logic sau này

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id")
    private Link link;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "places_category",
            joinColumns = @JoinColumn(name = "places_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "places_follow",
            joinColumns = @JoinColumn(name = "places_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "follow_id", referencedColumnName = "id")
    )
    private List<Follow> follows;
}

