package com.est14.senadoiudigital.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
// Esta clase rechaza todas las peticiones que no esten autenticadas
public class JwtEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
                        throws IOException, ServletException {

        log.error("Fallo en metodo comence");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
    }
}
