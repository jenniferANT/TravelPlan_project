package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
