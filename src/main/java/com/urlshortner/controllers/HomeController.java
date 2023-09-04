package com.urlshortner.controllers;

import com.urlshortner.models.Url;
import com.urlshortner.repositories.UrlRepository;
import com.urlshortner.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {

    @Autowired
    ControllerServices controllerServices;

    @GetMapping()
    public String displayIndex(){
        Url url = new Url("http://longurl.com");
        url.setShortUrl(controllerServices.getLastShortUrl());
        controllerServices.urlRepository.save(url);
        return url.getShortUrl() + "         " + url.getLongUrl();
    }
}
