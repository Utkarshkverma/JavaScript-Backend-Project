package com.example.spring.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class JwtService {

  private static final String SECRET = "TefCJURfpLixhrmuoNGlwTJ4lsFU96XBzT0GD0cFVGQLJCy8L+4sXXLFiequ2OfPwu9xMPOXH5psNyzzUymESYeqGhW68FxHv0ssHcuhVIb+b4pHKnyyIO42VRPYUsKBuFGgpPxjiYL7r0qIeSVfLK8Y6RMv3quLoyqfU7pjndfox+vuy+OgyCKRWqOJ0vJeHDb+2ezczG9Wwa/JUwAty7ibyN2A/l3milFCVHrT5+W38ZvizvjohE6bCbg3WzZad78LOc4S6TUpXctbZye+BOf0VmwEZTHN1A0PKs0GsJUfK97eEt6Jwq2FXv/r8qKtyuBxmhYzaD6PccNoeV7UvkJTTbjtDWvLtzvUrtT3AAE=";
  private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);
  public String generateToken(UserDetails userDetails) {
      Map<String, Object> claim = new HashMap<>();
      claim.put("iss","vermau2k01");
      return Jwts
              .builder()
              .addClaims(claim)
              .setSubject(userDetails.getUsername())
              .setIssuedAt(Date.from(Instant.now()))
              .setExpiration(Date.from(Instant.now().plusMillis(VALIDITY)))
              .signWith(getSecretKey())
              .compact();
  }

  private SecretKey getSecretKey() {
      byte[] decode = Base64.getDecoder().decode(SECRET);
      return Keys.hmacShaKeyFor(decode);
  }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserNameFromToken(String token) {
      return getAllClaimsFromToken(token).getSubject();
    }

    public boolean isTokenValid(String jwt) {
      Claims claims = getAllClaimsFromToken(jwt);
      Date expiration = claims.getExpiration();
      return expiration.after(Date.from(Instant.now()));
    }
}
