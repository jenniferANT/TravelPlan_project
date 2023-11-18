package com.app.travelplan.model.form;

import com.app.travelplan.model.entity.Address;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class AddressForm {
    private String addressString;
    private String addressLinkMap;
    private String addressPlusCode;
    private String embeddedAddress;
}
