package com.urlshortner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class JSONWebTokenService {

    @Autowired
    private JwtEncoder jwtEncoder; // managed BEAN

    public String generateToken(Authentication authentication){
        Instant currentTime = Instant.now();

        String scope = authentication.getAuthorities().stream() // stream one by one
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" ")); // builds string

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") // self issued certs
                .issuedAt(currentTime)
                .expiresAt(currentTime.plus(24, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); // return Token String
    }

}
