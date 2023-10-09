package com.urlshortner.models;

public class AddUrlDTO {

    private String longUrl;

//    User user; // eventually add a user
    String user;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
