package com.app.travelplan.model.form;

import lombok.Data;

@Data
public class ShareForm {
    private String message;
    private String[] receiverUsername;
    private long planId;
}
