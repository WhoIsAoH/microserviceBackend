//package org.srijan.incomeservice.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET_KEY = "yxxC6yc0IJIujgqxvOFT+bQaTDQ5OxgvwZuDdZdwjCHpDKXLby4ubwIdk7NfCMGis\n";
//    private SecretKey key;
//
//    @PostConstruct
//    public void init() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parser()
//                .verifyWith(key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            extractAllClaims(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    // 🔹 Extracts subject (usually email/username)
//    public String extractSubject(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    // 🔹 Extracts custom claim: userId
//    public Long extractUserId(String token) {
//        return extractAllClaims(token).get("userId", Long.class);
//    }
//
//    // 🔹 Extracts custom claim: roles
//    public String extractRole(String token) {
//        return extractAllClaims(token).get("roles", String.class);
//    }
//
//    // 🔹 Optional: extract firstName
//    public String extractFirstName(String token) {
//        return extractAllClaims(token).get("firstName", String.class);
//    }
//}