package com.managment.employeeManagment.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.DecryptAeadRequest;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

    // Generate JWT token
    public String GenerateToken(Authentication authentication){
        String userName = authentication.getName();

        Date currentdate = new Date();

        Date expireDate = new Date(currentdate.getTime() + jwtExpirationDate);

        String jwtToken =  Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(expireDate).signWith(key()).compact();

        return jwtToken;

    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get user name from jwy token
    public String getUSerNameFromJwtToken(String token){
        Claims claims =  Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        String userName = claims.getSubject();
        return userName;
    }

    // Validate the JWT token
    public boolean validateJwtToke(String token){
        Jwts.parser().setSigningKey(key()).build().parse(token);
        return true;
    }


}
