package com.urlshortner.controllers;

import com.urlshortner.models.AddUrlDTO;
import com.urlshortner.models.Url;
import com.urlshortner.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //allow requests from React
@RestController
@RequestMapping("/api/")
public class ApiController {

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
    public ResponseEntity<?> processLongUrl(@RequestBody AddUrlDTO addUrlDTO){
        Url url = new Url(addUrlDTO.getLongUrl());
        url.setShortUrl(controllerServices.getLastShortUrl());
        controllerServices.urlRepository.save(url);
//        return ResponseEntity.ok().build(); // send 200, maybe change this to return the Url details
        return ResponseEntity.ok(url); // return url information as json to update the user in react
    }


}
