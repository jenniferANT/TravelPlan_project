package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Address;
import com.app.travelplan.model.entity.User;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class AddressDto {
    private long id;
    private double latitude;
    private double longitude;
    private String addressString;
    private String addressLinkMap;
    private String addressPlusCode;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public static AddressDto toDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .addressString(address.getAddressString())
                .addressLinkMap(address.getAddressLinkMap())
                .addressPlusCode(address.getAddressPlusCode())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }
}
