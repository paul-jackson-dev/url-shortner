package com.urlshortner.models;

public class AddUrlDTO {

    private String longUrl;

//    User user; // eventually add a user

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    //    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
