package com.urlshortner.controllers;

import com.urlshortner.models.Url;
import com.urlshortner.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    ControllerServices controllerServices;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> displayIndex(){
        Url url = controllerServices.getLastUrl();
        String resource = url.getTopLevelDomain() + url.getShortUrl();
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/add") //change this to post once react is up
    @ResponseBody
    public String processLongUrl(@RequestParam String longUrl){
        Url url = new Url(longUrl);
        url.setShortUrl(controllerServices.getLastShortUrl());
        controllerServices.urlRepository.save(url);
        return url.getTopLevelDomain() + url.getShortUrl();
    }

    @GetMapping("/{shortUrl}")
//    @ResponseBody
    public ResponseEntity<?> returnRedirectUrl(@PathVariable String shortUrl){
        String resource = controllerServices.getRedirectUrl(shortUrl);
        return ResponseEntity.ok(resource);
    }
}
