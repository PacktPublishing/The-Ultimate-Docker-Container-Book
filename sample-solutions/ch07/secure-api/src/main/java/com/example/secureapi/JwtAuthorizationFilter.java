package com.example.secureapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String SECRET_KEY = "mysecretkey";
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing JWT");
            chain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWT.require(algorithm)
                    .build()
                    .verify(jwt);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
            return;
        }

        var authorization = new UsernamePasswordAuthenticationToken(jwt, null, emptyList());
        SecurityContextHolder.getContext().setAuthentication(authorization);
        chain.doFilter(request, response);

    }
}
