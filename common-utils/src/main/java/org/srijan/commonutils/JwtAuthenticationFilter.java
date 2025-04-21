package org.srijan.commonutils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer "

            try {
                if (jwtUtil.validateToken(jwt)) {
                    Integer userId = jwtUtil.extractUserId(jwt);
                    request.setAttribute("userId", userId);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
                    return;
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT processing error: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
