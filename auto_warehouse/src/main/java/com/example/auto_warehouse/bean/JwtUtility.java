package com.example.auto_warehouse.bean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtility {
    @Value("${jwt.secret}")
    private String secretKey="20301164";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        byte[] signingKey = secretKey.getBytes();

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);

        long expiresIn = 3600000; // Token有效期为一周

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("myapp.com")
                .setIssuedAt(date)
                .setExpiration(new Date(currentTimeMillis + expiresIn))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getAllClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
}

