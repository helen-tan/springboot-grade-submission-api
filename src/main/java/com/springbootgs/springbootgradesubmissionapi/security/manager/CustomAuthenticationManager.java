package com.springbootgs.springbootgradesubmissionapi.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.springbootgs.springbootgradesubmissionapi.entity.User;
import com.springbootgs.springbootgradesubmissionapi.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{
    
    private UserService userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userServiceImpl.getUser(authentication.getName());

        // Check if the hashed version of the user input pw matches the one we have in the DB
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Wrong Credentials"); // will invoke unsuccessfulAuthentication() in Authentication filter
        }

        // Successful Auth: Return Authentication object
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword()); // will invoke successfulAuthenticatoin() in Authentication filter 
    }
}
