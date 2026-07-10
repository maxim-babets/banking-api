package com.banking.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "my-super-secret-key-for-banking-api-jwt-token";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    private final SecretKey key = Keys.hmacShaKeyFor(
            SECRET.getBytes(StandardCharsets.UTF_8)
    );



    public String generateToken(String email){
    Date now = new Date();
    Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

    return Jwts.builder()
            .subject(email)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key)
            .compact();
    }

    public String extractEmail(String token){
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public Date extractExpiration(String token){
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return claims.getExpiration();
    }

    public boolean isTokenValid(String token, String email){
        String emailFromToken = extractEmail(token);
        String emailFromDb = email;
        Date expiration = extractExpiration(token);
        if(emailFromToken.equals(emailFromDb) && expiration.after(new Date())){
            return true;
        }
        return false;
    }

}