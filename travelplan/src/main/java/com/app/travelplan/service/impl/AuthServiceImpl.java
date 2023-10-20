package com.app.travelplan.service.impl;

import com.app.travelplan.exception.InvalidRefreshTokenException;
import com.app.travelplan.model.dto.AuthDto;
import com.app.travelplan.model.entity.Role;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.model.form.LoginForm;
import com.app.travelplan.model.form.RegisterForm;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.repository.RoleRepository;
import com.app.travelplan.repository.UserRepository;
import com.app.travelplan.security.JwtService;
import com.app.travelplan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ImageRepository imageRepository;

    @Override
    public AuthDto login(LoginForm form) {
        User user = userRepository.findByUsername(form.getUsername())
                .orElseThrow(() ->
                        new IllegalArgumentException("Username not found"));
        if(!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password valid");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword())
        );
        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateRefreshToken(authentication);
        return AuthDto.from(authentication, accessToken, refreshToken);
    }

    @Override
    public String register(RegisterForm form) {
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Not found ROLE_USER"));

        if(userRepository.existsUserByUsername(form.getUsername())) {
            throw new IllegalArgumentException("Username exists!");
        }

        //set avatar mặc định
        var user = User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .role(role)
                .email(form.getEmail())
                .build();

        userRepository.save(user);
        return "Success register new user";
    }

    @Override
    public AuthDto refreshJWT(String refreshToken) {
        if( refreshToken != null ){
            refreshToken = refreshToken.replaceFirst("Bearer ", "");
            if(jwtService.validateRefreshToken(refreshToken) ){
                Authentication auth = jwtService.createAuthentication(refreshToken);
                return AuthDto.from(auth, jwtService.generateAccessToken(auth), refreshToken);
            }
        }
        throw new InvalidRefreshTokenException(refreshToken);
    }
}
