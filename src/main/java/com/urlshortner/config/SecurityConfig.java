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
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

//    @Bean //Attempts to authenticate the passed Authentication object, returning a fully populated Authentication object (including granted authorities) if successful.
//    public AuthenticationManager authenticationManager(CustomUserDetailsService userDetailsService){
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); // validates Username/Password using the Bean PasswordEncoder
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider); // returns an AuthenticationManager
    }

    @Bean //TODO default username until I set up user database
    @Primary
    public UserDetailsService user(){
        return new InMemoryUserDetailsManager(
                User.withUsername("555")
                        .password("{noop}555")
                        .authorities("read")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable()) // disable cross site request forgery // leaving sessionManagement enabled will open app up to attack
                .authorizeRequests(auth ->  auth
                                .requestMatchers("/api/token").permitAll()
                                .anyRequest().authenticated()
                        )  // all requests are authed
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // :: is a method reference // jwt requires JwtDecoder Bean
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //turn off session management because we are using just an API
//                .httpBasic(Customizer.withDefaults()) // sets up default form login
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

    @Bean
    PasswordEncoder passwordEncoder(){ // SpringSecurity will automatically use this for username/password validation
        return new BCryptPasswordEncoder();
    }

}
