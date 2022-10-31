package com.est14.senadoiudigital.security.jwt;

import com.est14.senadoiudigital.security.model.MainUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


// Crea un token y lo valida
@Component
@Slf4j
public class JwtProvider {

    @Value("${est14.jwt.token.secret:secret}")
    private String SECRET;
    @Value("${est14.jwt.token.expires-in:3600}")
    private int EXPIRES_IN;
    @Value("${est14.jwt.issuer:none}")
    private String ISSUER;


    // Crea el token
    public  String generateToken(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        log.info("JwtProvider - Generate token " + mainUser.getUsername());
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                // Realmente este es el email
                .setSubject(mainUser.getUsername())
                .setExpiration(new Date(new Date().getTime() + EXPIRES_IN * 1000L))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    // Obtenen nombre desde el token
    public String getEmailFromToken(String token){

        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Valida el token
    public boolean validateToken(String token){
        log.info("JwtProvider - validateToken ");
        try{
            Jwts.parserBuilder()
                    .setSigningKey(SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token);
            log.info("Token Validado");
            return true;
        }catch (MalformedJwtException e){
            log.error("Token mal formado");
        }catch (ExpiredJwtException e){
            log.error("Token expirado --> " + e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("Token no soportado");
        }catch (IllegalArgumentException e){
            log.error("Token vacio");
        }catch (SignatureException e){
            log.error("fallo con la firma del token");
        }
        return false;
    }
}
