package com.springbootgs.springbootgradesubmissionapi.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.springbootgs.springbootgradesubmissionapi.security.SecurityConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Authorize Client requests if and only if the JWT token they give is valid
// Verify signature inside token (header-payload-signature made from secret key) to see if valid
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    // Authorization: Bearer JWT token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Verify JWT token inside the client's request
        String header = request.getHeader("Authorization"); // Bearer JWT

        // For /register path: no need token
        // If there is no jwt sent in, just go to the next filter
        if (header == null || !header.startsWith(SecurityConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace(SecurityConstants.BEARER, "");

        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
                .build()
                .verify(token) // will return a decoded JWT
                .getSubject(); // username
        
        // Set the Authentication object on the Security Context Holder (where Spring stores details of who is authenticated)
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Authorize user to perform action
        filterChain.doFilter(request, response);
    }
}
