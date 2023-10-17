package com.urlshortner.controllers;

import com.urlshortner.services.JSONWebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") //allow requests from React
@RestController
public class AuthController {

    @Autowired
    private JSONWebTokenService jsonWebTokenService;

     @PostMapping("/api/token")
    public String token(Authentication authentication){
         System.out.println("Token request for user: " + authentication.getName());
         String token = jsonWebTokenService.generateToken(authentication);
         System.out.println(token);
         return token;
     }
}
