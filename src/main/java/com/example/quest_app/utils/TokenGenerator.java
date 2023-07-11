package com.example.quest_app.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.quest_app.exception.GenericException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenGenerator {
    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;
    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private long EXPIRES_ACCESS_TOKEN_MINUTE;

    public String generateToken(Authentication authentication){
        String userName = ((UserDetails)authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        + (EXPIRES_ACCESS_TOKEN_MINUTE*60*1000)))
                        .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));

    }

    public DecodedJWT verifyJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try{
            return  verifier.verify(token);

        }catch(Exception e){
            throw new GenericException();
        }

    }
}
