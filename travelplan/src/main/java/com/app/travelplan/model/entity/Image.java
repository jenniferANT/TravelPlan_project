package com.app.travelplan.model.entity;

import com.app.travelplan.utils.AppUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "data", unique = false, nullable = false, length = 1000000)
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public String getUrlImage() {
        return AppUtils.getBaseUrl() + "/api/v1/image/get/" + this.id;
    }
}
