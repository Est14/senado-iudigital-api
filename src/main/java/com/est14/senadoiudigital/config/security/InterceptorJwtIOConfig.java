package com.est14.senadoiudigital.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@RequiredArgsConstructor
public class InterceptorJwtIOConfig implements WebMvcConfigurer {

    @Value("${est14.jwt.security.enable:false}")
    private boolean securityEnable;

    private final InterceptorJwtIO interceptorJwtIO;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (securityEnable){
            registry.addInterceptor(interceptorJwtIO);
        }
    }
}
