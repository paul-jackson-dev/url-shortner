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

    public String getTopLevelDomain() {
        return topLevelDomain;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    // this method is basically a counter. there is probably a more simple way to write it.
    public void setShortUrl(String lastShortUrl) {
        String characters = "abcdefghijklmnopqrstuvxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
//        String characters = "abc" + "9"; //TODO remove after testing
        if (lastShortUrl == null) {
            this.shortUrl = characters.substring(0, 1); // "a"
            return;
        }

        int length = lastShortUrl.length();

        if(lastShortUrl.startsWith("9") && lastShortUrl.endsWith("9")){
            lastShortUrl = "";
            for(int i = 0 ; i < length +1; i++) {
                lastShortUrl += characters.substring(0, 1); // "99" becomes "aaa"
            }
            this.shortUrl = lastShortUrl;
        }
        else if (lastShortUrl.endsWith("9")){
            int indexOfNine = lastShortUrl.indexOf("9");
            lastShortUrl = lastShortUrl.replaceAll("9",characters.substring(0, 1));
            String lastChar = lastShortUrl.substring(indexOfNine -1, indexOfNine);
            int lastCharIndex = characters.indexOf(lastChar);
            String nextChar = characters.substring(lastCharIndex + 1, lastCharIndex + 2);
            this.shortUrl = lastShortUrl.substring(0,indexOfNine -1) + nextChar + lastShortUrl.substring(indexOfNine); // "aa9" becomes "aba"
        }
        else {
            String lastChar = lastShortUrl.substring(length - 1);
            int lastCharIndex = characters.indexOf(lastChar);
            String nextChar = characters.substring(lastCharIndex + 1, lastCharIndex + 2);
            this.shortUrl = lastShortUrl.substring(0, length - 1) + nextChar; // "aaa" becomes "aab"
        }
    }
}
