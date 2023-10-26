package com.urlshortner.models;

import com.urlshortner.config.SecurityConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String email;

    private String passwordHash;

    private String roles; // comma separated list of roles "ROLE_USER,ROLE_ADMIN" default is "ROLE_USER"

    private static final PasswordEncoder passwordEncoder = SecurityConfig.passwordEncoder();

    public User(){}

    public User(String username, String email, String password, String roles) {
        this.username = username;
        this.email = email;
        this.passwordHash = "{bcrypt}" + passwordEncoder.encode(password); // {bcrypt} helps the Security Config PasswordEncoder identify the hash
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isPasswordMatching(String password){
        return passwordEncoder.matches(password,passwordHash); // can't use .equals because of salting
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = "{bcrypt}" + passwordEncoder.encode(password);
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
