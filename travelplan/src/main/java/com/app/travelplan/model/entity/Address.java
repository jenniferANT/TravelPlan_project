package com.app.travelplan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "address_string")
    private String addressString;

    @Column(name = "address_link_map", length = 2048)
    private String addressLinkMap;

    @Column(name = "address_plus_code", length = 2048)
    private String addressPlusCode;

    @Column(name = "embedded_address", length = 2048)
    private String embeddedAddress;

    @OneToOne(mappedBy = "address")
    private Places places;
}
