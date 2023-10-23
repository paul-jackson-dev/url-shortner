package com.urlshortner.controllers;

import com.urlshortner.services.JSONWebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") //allow requests from React
@RestController
public class AuthController {

    private final JSONWebTokenService jsonWebTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JSONWebTokenService jsonWebTokenService, AuthenticationManager authenticationManager) {
        this.jsonWebTokenService = jsonWebTokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/token")
    public String token(Authentication authentication){
         System.out.println("Token request for user: " + authentication.getName());
         String token = jsonWebTokenService.generateToken(authentication);
         System.out.println(token);
         return token;
     }
//    @GetMapping("/api/token")
//    public String tokenGet(Authentication authentication){
//        System.out.println(authentication);
//        System.out.println("Token request for user: " + authentication.getName());
//        String token = jsonWebTokenService.generateToken(authentication);
//        System.out.println(token);
//        return token;
//    }
}
