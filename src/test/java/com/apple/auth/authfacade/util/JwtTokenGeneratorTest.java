package com.apple.auth.authfacade.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenGeneratorTest {

    @Test
    public void generateJwtToken() {
        String secret = "MySuperSecureJwtKeyThatIsAtLeast32Bytes!";
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_USER");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject("test-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (10 * 60 * 1000))) // 10 minutes
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("\n=== Paste this token into Postman or curl ===");
        System.out.println("Authorization: Bearer " + jwt + "\n");
    }
}