package com.urlshortner.controllers;

import com.urlshortner.models.Url;
import com.urlshortner.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //allow requests from React
@RestController
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    ControllerServices controllerServices;

    @GetMapping()
//    @ResponseBody
    public ResponseEntity<?> displayIndex(){
//        Url url = controllerServices.getLastUrl();
        List<Url> urls = controllerServices.getAllUrls();
//        String resource = url.getTopLevelDomain() + url.getShortUrl();
        if (urls == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/add") //change this to post once react is up
//    @ResponseBody
    public String processLongUrl(@RequestParam String longUrl){
        Url url = new Url(longUrl);
        url.setShortUrl(controllerServices.getLastShortUrl());
        controllerServices.urlRepository.save(url);
        return url.getTopLevelDomain() + url.getShortUrl();
    }

    @GetMapping("/{shortUrl}")
//    @ResponseBody // this will automatically convert the response to JSON
    public ResponseEntity<?> returnRedirectUrl(@PathVariable String shortUrl){
        String resource = controllerServices.getRedirectUrl(shortUrl);
        return ResponseEntity.ok(resource);
    }
}
