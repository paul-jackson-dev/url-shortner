package com.urlshortner.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Url {

    @Id
    @GeneratedValue()
    private int id;

    private String longUrl;

    private String shortUrl;

    private String topLevelDomain = "http://localhost/";

    public Url(){}

    public Url(String longUrl) {
        this.longUrl = longUrl;
    }

    public int getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String lastShortUrl) {
        String characters = "abcdefghijklmnopqrstuvxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        if (lastShortUrl == null){
            this.shortUrl = characters.substring(0,1); // "a"
        }

        else if (lastShortUrl.endsWith("9")){ // if characters are exhausted, start again
            this.shortUrl = lastShortUrl + characters.substring(0,1); // "a9" + "a"
        }

        else{
            int length = lastShortUrl.length();
            String lastChar = lastShortUrl.substring(length-1);
            int lastCharIndex = characters.indexOf(lastChar);
            String nextChar = characters.substring(lastCharIndex +1, lastCharIndex +2);
            this.shortUrl = lastShortUrl.substring(0,length -1) + nextChar; // remove the last character and replace it with the next character
        }
    }
}
