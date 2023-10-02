package com.urlshortner.controllers;

import com.urlshortner.models.AddUrlDTO;
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

    @GetMapping("shorturls")
//    @ResponseBody
    public ResponseEntity<?> displayIndex(){
//        Url url = controllerServices.getLastUrl();
        List<Url> urls = controllerServices.getAllUrls();
//        String resource = url.getTopLevelDomain() + url.getShortUrl();
        if (urls == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println(urls);
        return ResponseEntity.ok(urls);
    }

    @PostMapping("/add")
//    public ResponseEntity<?> processLongUrl(@RequestBody AddUrlDTO addUrlDTO){
    public ResponseEntity<?> processLongUrl(@RequestBody String text){
//        Url url = new Url(addUrlDTO.getLongUrl());
        System.out.println(text);
        Url url = new Url(text);
        url.setShortUrl(controllerServices.getLastShortUrl());
        controllerServices.urlRepository.save(url);
//        return url.getTopLevelDomain() + url.getShortUrl();
        return ResponseEntity.ok().build(); // send 200, maybe change this to return the Url details
    }

    @GetMapping("/{shortUrl}")
//    @ResponseBody // this will automatically convert the response to JSON
    public ResponseEntity<?> returnRedirectUrl(@PathVariable String shortUrl){
        String resource = controllerServices.getRedirectUrl(shortUrl);
        return ResponseEntity.ok(resource);
    }
}
