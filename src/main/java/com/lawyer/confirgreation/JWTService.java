package com.lawyer.confirgreation;


import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;

import javax.xml.crypto.Data;
import java.security.Key;
import java.security.Signature;
import java.util.Date;

@Service
public class JWTService {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMS= 864000000; // 10 days

    public String generateJWTToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationMS))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){

        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }
        catch (JwtException e){
                return null;
        }
    }

    public boolean isTokenExpired(String token){
        try{
            Date expiration=Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public boolean  isTokenValid(String token){
        return !isTokenExpired(token) && extractUsername(token)!=null;
    }
}
