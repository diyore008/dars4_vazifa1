package com.example.dars4_vazifa1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    String secretWord = "what.what.what";
    long expireDate = 36_000_000;
    public String generateToken(String username){
        Date expire = new Date(System.currentTimeMillis() + expireDate);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, secretWord)
                .compact();
        return token;
    }

    public boolean validateToken(String token){
        try{
            Jwts
                    .parser()
                    .setSigningKey(secretWord)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        String username = Jwts
                .parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }

}