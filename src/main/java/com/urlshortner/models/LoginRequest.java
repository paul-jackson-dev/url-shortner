package com.urlshortner.models;

public record LoginRequest(String username, String password) { // records are immutable, DTOs are not.
}
