package com.lawyer.confirgreation;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

//
//    private static final String SECRET_KEY = "my-super-secret-key-1234567890123456"; // 32+ chars
//    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
//
//    private final long expirationMS = 864000000; // 10 days
//
//    public String generateJWTToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationMS))
//                .signWith(key)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//        } catch (JwtException e) {
//            return null;
//        }
//    }
//
//    public boolean isTokenExpired(String token) {
//        try {
//            Date expiration = Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getExpiration();
//            return expiration.before(new Date());
//        } catch (Exception e) {
//            return true;
//        }
//    }
//
//    public boolean isTokenValid(String token) {
//        return extractUsername(token) != null && !isTokenExpired(token);
//    }


    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Consider loading this from secure config
    private final long expirationMs = 864000000; // 10 days

    public String generateJWTToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        return extractUsername(token) != null && !isTokenExpired(token);
    }

    // 🔓 Made public so it can be accessed outside this class
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (JwtException e) {
            return true;
        }
    }



}
