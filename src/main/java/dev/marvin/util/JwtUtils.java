package dev.marvin.util;


import dev.marvin.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(Authentication authentication) {
            Map<String, Object> claims = new HashMap<>();
            User user = (User) authentication.getPrincipal();
            claims.put("userId", user.getId());
            claims.put("role", user.getRole().name());

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + (1000 * 60 * 60)); // 1 hour in milliseconds

            return Jwts.builder()
                    .setIssuer("meliora@wallet.co.ke")
                    .setSubject(user.getUsername())
                    .addClaims(claims)
                    .setExpiration(expiryDate)
                    .setIssuedAt(now)
                    .signWith(key)
                    .compact();
    }
}
