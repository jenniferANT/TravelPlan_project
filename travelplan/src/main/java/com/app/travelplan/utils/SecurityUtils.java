package com.app.travelplan.utils;

import com.app.travelplan.model.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getUsernameOfPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) return auth.getName();
        return null;
    }

    public static String getRoleOfPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get();
    }
}