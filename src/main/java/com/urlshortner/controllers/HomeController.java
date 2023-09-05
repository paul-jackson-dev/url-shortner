package com.urlshortner.controllers;

import com.urlshortner.models.Url;
import com.urlshortner.repositories.UrlRepository;
import com.urlshortner.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HomeController {

    @Autowired
    ControllerServices controllerServices;

    @GetMapping()
    @ResponseBody
    public String displayIndex(){
        Url url = controllerServices.getLastUrl();
        return url.getTopLevelDomain() + url.getShortUrl();
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
    @ResponseBody
    public String returnRedirectUrl(@PathVariable String shortUrl){
        return controllerServices.getRedirectUrl(shortUrl);
    }
}
