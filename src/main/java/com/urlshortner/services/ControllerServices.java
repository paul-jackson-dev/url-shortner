package com.urlshortner.services;

import com.urlshortner.models.Url;
import com.urlshortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ControllerServices {

    @Autowired
    public UrlRepository urlRepository;

    public String getLastShortUrl(){
        Optional<Url> urlResult = urlRepository.findFirstByOrderByIdDesc();
        if (urlResult.isPresent()){
            return urlResult.get().getShortUrl();
        }
        return null;
    }
}
