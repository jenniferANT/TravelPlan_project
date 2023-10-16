package com.app.travelplan.controller;

import com.app.travelplan.model.dto.AuthDto;
import com.app.travelplan.model.form.LoginForm;
import com.app.travelplan.model.form.RegisterForm;
import com.app.travelplan.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm form){
        return new ResponseEntity(authService.login(form), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity login(@RequestBody RegisterForm form){
        return new ResponseEntity(authService.register(form), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken){
        return new ResponseEntity(authService.refreshJWT(refreshToken), HttpStatus.OK);
    }
}
