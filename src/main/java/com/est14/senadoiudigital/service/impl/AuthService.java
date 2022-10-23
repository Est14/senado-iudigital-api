package com.est14.senadoiudigital.service.impl;

import com.est14.senadoiudigital.common.utils.DateUtil;
import com.est14.senadoiudigital.config.security.JwtIO;
import com.est14.senadoiudigital.dto.JwtResponse;
import com.est14.senadoiudigital.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIO jwtIO;
    private final DateUtil dateUtil;

    @Value("${est14.jwt.token.expires-in}")
    private int EXPIRES_IN;
    public JwtResponse login(String clientId, String clientSecret){

        UsuarioDto usuario = UsuarioDto.builder()
                .nombre("Esteban Martinez")
                .email("est14@gmail.com")
                .role("ADMIN")
                .idUsuario(1111)
                .build();


        return JwtResponse.builder()
                .tokenType("bearer")
                // This is the payload that backend return
                .accessToken(jwtIO.generateToken(usuario))
                .issuedAt(dateUtil.getDateMillis()+ "")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN) // This value is in second so, 3600 sec = 1 hour
                .build();
    }
}
