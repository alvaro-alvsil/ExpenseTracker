package com.alvaro.practica.expense_tracker.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenJwtConfig {

    public static final String PREFIX_TOKEN = "Bearer ";    
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    private final SecretKey secretKey;
    private final long expirationMs;

    public TokenJwtConfig(
        @Value("${jwt.secret}") String jwtSecret,
        @Value("${jwt.expiration-ms}") long expirationMs) {
        if (jwtSecret == null || jwtSecret.isBlank()) {
            throw new IllegalStateException("La propiedad 'jwt.secret' debe estar configurada (por ejemplo vía variable de entorno JWT_SECRET).");
        }

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes); 
        this.expirationMs = expirationMs;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}
