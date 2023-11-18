package com.app.travelplan.model.dto;

import com.app.travelplan.model.entity.Address;
import com.app.travelplan.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String embeddedAddress;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    protected LocalDateTime createdAt;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
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
                .embeddedAddress(address.getEmbeddedAddress())
                .build();
    }
}
