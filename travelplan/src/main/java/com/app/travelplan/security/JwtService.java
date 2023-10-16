package com.app.travelplan.security;

import com.app.travelplan.model.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class JwtService {
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;

    public JwtService(JwtProperties jwtProperties, UserDetailsService userDetailsService) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
    }

    public String generateAccessToken(Authentication auth){
        return jwtProperties.getPrefix() + JWT.create()
                .withExpiresAt(Instant.now().plusMillis(jwtProperties.getAccessExpiresAt()))
                .withSubject(auth.getName())
                .withClaim("role", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null))
                .sign(Algorithm.HMAC512(jwtProperties.getAccessSecret()));
    }

    public String generateRefreshToken(Authentication auth){
        return jwtProperties.getPrefix() + JWT.create()
                .withExpiresAt(Instant.now().plusMillis(jwtProperties.getRefreshExpiresAt()))
                .withIssuedAt(Instant.now())
                .withSubject(auth.getName())
                .sign(Algorithm.HMAC512(jwtProperties.getRefreshSecret()));
    }

    public boolean validateAccessToken(String token){
            DecodedJWT jwt = JWT.require( Algorithm.HMAC512(jwtProperties.getAccessSecret()) )
                    .acceptExpiresAt( jwtProperties.getAccessExpiresAt() ) //Kiểm tra xem token có hết hạn hay không
                    .withClaimPresence("sub")
                    .withClaimPresence("role")
                    .build()
                    .verify( token );

            String username = jwt.getSubject();
            User user = (User) userDetailsService.loadUserByUsername(username);
            if( !user.isEnabled() )
                return false;

            String tokenRole = jwt.getClaim("role").asString();
            return Objects.equals(user.getRole().getName(), tokenRole);
    }

    public boolean validateRefreshToken(String token){
            JWT.require( Algorithm.HMAC512(jwtProperties.getRefreshSecret()) )
                    .acceptExpiresAt( jwtProperties.getRefreshExpiresAt() )
                    .withClaimPresence("sub")
                    .withClaimPresence("iat")
                    .build()
                    .verify( token );
            return true;
    }

    public String extractToken(HttpServletRequest request){
        String authHeader = request.getHeader( jwtProperties.getAuthHeader() );
        if(authHeader == null || !authHeader.startsWith( jwtProperties.getPrefix() ))
            return null;
        return authHeader.replaceFirst(jwtProperties.getPrefix(), "" );
    }

    public Authentication createAuthentication(String token){
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}
