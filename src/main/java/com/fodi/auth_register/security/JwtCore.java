package com.fodi.auth_register.security;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.util.Date;
@Slf4j
@Component
public class JwtCore {
    @Value("${app.secret}")
    private String secret;
    @Value("${app.lifetime}")
    private int lifetime;

    public String generateToken(Authentication auth) {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setSubject(user.getPhoneNumber())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifetime))
                .signWith(key)
                .compact();

    }

    public String getNameFromJwt(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims =  Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        log.info(claims.toString());
        log.info(claims.getSubject());
        return claims.getSubject();
    }
}
