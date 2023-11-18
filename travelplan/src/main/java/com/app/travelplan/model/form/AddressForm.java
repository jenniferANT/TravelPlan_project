package com.app.travelplan.model.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter @Setter
public class AddressForm {
    @NotBlank
    private String addressString;
    @NotBlank
    private String addressLinkMap;
    private String addressPlusCode;
    @NotBlank
    private String embeddedAddress;
}
