package com.app.travelplan.service;

import com.app.travelplan.model.dto.AuthDto;
import com.app.travelplan.model.form.LoginForm;
import com.app.travelplan.model.form.RegisterForm;

public interface AuthService {
    AuthDto login(LoginForm form);
    String register(RegisterForm form);
    AuthDto refreshJWT(String refreshToken);
}
