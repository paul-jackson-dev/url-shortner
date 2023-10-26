package com.urlshortner.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.urlshortner.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RsaKeyProperties rsaKeys;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(RsaKeyProperties rsaKeys, CustomUserDetailsService customUserDetailsService) {

        this.rsaKeys = rsaKeys; // populate with constructor injection
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() { // SpringSecurity will automatically use this for username/password validation
        return new BCryptPasswordEncoder();
    }

    @Bean //Attempts to authenticate the passed Authentication object, returning a fully populated Authentication object (including granted authorities) if successful.
    public AuthenticationManager authenticationManager(CustomUserDetailsService customUserDetailsService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); // validates Username/Password using the Bean PasswordEncoder
        authProvider.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(authProvider); // returns an AuthenticationManager
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable()) // disable cross site request forgery // leaving sessionManagement enabled will open app up to attack
                .authorizeRequests(auth ->  auth
                        .requestMatchers("","/","/api","/api/token").permitAll()
                        .anyRequest().authenticated())  // all requests are authed
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())) // jwt requires JwtDecoder Bean
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //turn off session management because we are using just an API
                .build();
                    }

    @Bean
    JwtDecoder jwtDecoder(){ //automatically deciphers a jwt
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build(); // JSON Web Key
        JWKSource< SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk)); // creates JSON Web Key Set using keys.
        return new NimbusJwtEncoder(jwkSource);
    }

}
