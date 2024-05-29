package com.example.demo.config;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    public String extractUsername(String jwt) {
        return null;
    }

    @SuppressWarnings({ "deprecation", "unused" })
    private Claims extractAllClaims(String token)
    {
        
        return Jwts.parser()
        .setSigningKey(getSingInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();

    }

    private byte[] getSingInKey() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingInKey'");
    }

}

// 53:04