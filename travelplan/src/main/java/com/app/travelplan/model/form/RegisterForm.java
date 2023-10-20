package com.app.travelplan.model.form;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegisterForm {
    private String username;
    private String password;
    private String name;
    @Email
    private String email;
}
