package com.urlshortner.services;

import com.urlshortner.models.Url;
import com.urlshortner.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControllerServices {

    @Autowired
    public UrlRepository urlRepository;

    public String getLastShortUrl(){
        Optional<Url> urlResult = urlRepository.findFirstByOrderByIdDesc();
        return urlResult.map(Url::getShortUrl).orElse(null); //suggested by intellij, stream map
    }

    public String getRedirectUrl(String shortUrl){
        Optional<Url> urlResult = urlRepository.findByShortUrl(shortUrl);
        if (urlResult.isPresent()){
            return urlResult.get().getLongUrl();
        }
        return null;
    }

    public Url getLastUrl(){
        Optional<Url> urlResult = urlRepository.findFirstByOrderByIdDesc();
        if (urlResult.isPresent()){
            return urlResult.get();
        }
        return null;
    }

    public List <Url> getAllUrls(){
        List<Url> Urls = urlRepository.findAll();
        if (!Urls.isEmpty()){
            return Urls;
        }
        return null;
    }
}
