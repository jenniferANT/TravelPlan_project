package com.app.travelplan.model.form;

import com.app.travelplan.model.entity.Address;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class AddressForm {
    private double latitude;
    private double longitude;
    private String addressString;
    private String addressLinkMap;
    private String addressPlusCode;
    private String embeddedAddress;

    public AddressForm(double latitude, double longitude, String addressString, String addressLinkMap, String addressPlusCode, String embeddedAddress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressString = addressString;
        this.addressLinkMap = addressLinkMap;
        this.addressPlusCode = addressPlusCode;
        this.embeddedAddress = embeddedAddress;
    }
}
