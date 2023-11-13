package com.app.travelplan.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "plan_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanItem extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2048)
    private String imageUrl;
    private LocalDateTime startTime;
    private String title;
    @Column(length = 2048)
    private String content;
    private BigDecimal money;
    private String note;
    @Column(name = "is_optional")
    private boolean isOptional;
    //0: đêm - 1: ăn sáng - 2: buổi sáng - 3: buổi trưa - 4: buổi chiều - 5: tắm rửa - 6: ăn tối - 7: buổi tối
    private int index;

    @Column(name = "place_id")
    private long placesId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private Plan plan;
}
