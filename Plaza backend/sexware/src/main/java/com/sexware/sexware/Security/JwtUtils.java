package com.sexware.sexware.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils {


    private final String SECRET_KEY = "pl4z4S0ftw4r3";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
    }

    public String obtenerRol(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
        List<String> rol = Collections.singletonList(claims.get("rol", String.class));
        return rol.get(0);

    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {

        List<String> roles = (List<String>) userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String rol="";
        for (String rols:roles){
            rol = rols;
        }

        return createToken(rol, userDetails.getUsername());
    }

    private String createToken(String rol, String subject) {

        return Jwts.builder()
                .claim("rol",rol)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
