package com.springbootgs.springbootgradesubmissionapi.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootgs.springbootgradesubmissionapi.entity.User;
import com.springbootgs.springbootgradesubmissionapi.security.manager.CustomAuthenticationManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private CustomAuthenticationManager authenticationManager;

    // first method to be invoked when request is made on /authenticate
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // Retrieve body of request - retrieve username & password
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            // Create Authentication Object 
            // Pass object into authentication manager - for it to return here upon successful authentication
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return authenticationManager.authenticate(authentication);

            // System.out.println(user.getUsername());
            // System.out.println(user.getPassword());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // If AuthenticationException is thrown in the Authenticatoin Manager, this gets invoked
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        System.out.println("Boohoo authentication failed!");
    }

    // successfulAuthentication() will get invoked once the authentication object is return upon successful auth in the Authentication Manager
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        System.out.println("Woohoo authentication worked!"); 
    }
    
}
