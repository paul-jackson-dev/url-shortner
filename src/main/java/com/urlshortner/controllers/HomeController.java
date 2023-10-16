package com.urlshortner.controllers;

import com.urlshortner.models.Url;
import com.urlshortner.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

//@CrossOrigin(origins = "http://localhost:3000") //allow requests from React
@RestController
@RequestMapping("")
public class HomeController {

    @Autowired
    ControllerServices controllerServices;

//    @GetMapping("/{shortUrl}")
//    public ResponseEntity<?> returnRedirectUrl(@PathVariable String shortUrl) throws URISyntaxException {
//        System.out.println(shortUrl);
//        Url url = controllerServices.getUrl(shortUrl);
//        url.addClick();
//        controllerServices.urlRepository.save(url);
//        URI uri = new URI(url.getLongUrl());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uri);
//        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER); //SEE_OTHER returns 303
//    }

    @GetMapping
    public String returnString(Principal principal){
        return "I've got your username, " + principal.getName();
    }
}
