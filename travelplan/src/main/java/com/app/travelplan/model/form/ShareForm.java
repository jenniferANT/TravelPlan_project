package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ShareForm {
    @NotNull
    private String message;
    @NotBlank
    private String[] receiverUsername;
    @NotBlank
    private long planId;
}
