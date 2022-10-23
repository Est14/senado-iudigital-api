package com.est14.senadoiudigital.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InterceptorJwtIO implements HandlerInterceptor {

    @Value("${est14.jwt.token.auth.path}")
    private String AUTH_PATH;

    @Value("#{'${est14.jwt.exclude.path}'.split(',')}")
    private List<String> excluded;

    private final JwtIO jwtIO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean validate = false;
        String uri = request.getRequestURI();
        log.info(uri);
        if(uri.equals(AUTH_PATH) || excluded(uri)) {
            log.info("Entra a requerir el token");
            validate = true;
        }

        if(!validate && request.getHeader("Authorization") != null
                && !request.getHeader("Authorization").isEmpty()){

            log.info("Entra a validar el token");

            String token = request.getHeader("Authorization").replace("Bearer ", "");
            log.info(token);
            validate = !jwtIO.validateToken(token);
        }

        if(!validate) response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

        return validate;
    }

    private boolean excluded(String path){

        boolean result = false;

        for (String exc: excluded) {
            if(!exc.equals("#") && exc.equals(path)){
                return true;
            }
        }

        return result;
    }
}
