package org.srijan.userservice.service;

import org.srijan.userservice.entity.UserDetail;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
     <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetail userDetail);
    String generateToken(Map<String, Object> extraClaims, UserDetail userDetail);
    boolean isTokenValid(String token, UserDetails userDetails);

}
