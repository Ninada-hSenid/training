package com.trainingprogram.springsecurity_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

   @Value("${jwt.secret}")
    private String secret ; //= "bW9uZ3VzX3NheXNfaGVsbG9fd29ybGRfbW9uZ3VzX3NheXNfaGVsbG9";

    @Value("${jwt.expiration}")
    private long expiration; // = 86400000;

    public String generateToken(UserDetails userDetails) {
        System.out.println("Generating token for: " + userDetails.getUsername());
        System.out.println("Authorities: " + userDetails.getAuthorities());

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))  // Properly extract authority strings
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                .compact();

        System.out.println("Generated token: " + token);
        return token;
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }


    // ✅ This method works with JwtAuthFilter
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            boolean isValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
            System.out.println("Token validation result: " + isValid);
            return isValid;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
