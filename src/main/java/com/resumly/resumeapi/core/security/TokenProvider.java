package com.resumly.resumeapi.core.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.resumly.resumeapi.modules.user.domain.Authority;
import com.resumly.resumeapi.modules.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TokenProvider {
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("SecretKeyToGenJWTs".getBytes()))
                .build()
                .verify(token.replace("Bearer ", ""));

        User user = new User();
        user.setEmail(String.valueOf(decodedJWT.getSubject()));
        user.setId(decodedJWT.getClaim("userId").asLong());
        user.setPassword(decodedJWT.getClaim("userPassword").asString());


        /*List<Authority> authorities = new ArrayList<>(decodedJWT.getClaim("authorities")
                .asList(String.class)).stream().map(new Function<String, Authority>() {
            @Override
            public Authority apply(String authorityName) {
                Authority authority = new Authority();
                authority.setName(authorityName);
                return authority;
            }
        }).collect(Collectors.toList());

        user.setAuthorities(Set.copyOf(authorities)); */

        return user;
    }


    public String generateToken(User user, String password) {

        /*List<String> authorities = user.getAuthorities().stream().map(new Function<GrantedAuthority, String>() {
            @Override
            public String apply(GrantedAuthority grantedAuthority) {
                return grantedAuthority.getAuthority();
            }
        }).collect(Collectors.toList());

        String[] authorities_ = new String[authorities.size()];
        authorities_ = authorities.toArray(authorities_); */

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("userPassword", password)
                .withClaim("userId", user.getId())
                //.withArrayClaim("authorities", authorities_)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(secret.getBytes()));


        return token;
    }
}
