package com.est14.senadoiudigital.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Data
@Configuration
@ConfigurationProperties(prefix = "est14.jwt")
public class JwtIOProperties {

    // Este atributo activa o desactiva la seguridad
    private Security security;
    private String timezone;
    private String issuer;
    private  Token token;
    private Excluded excluded;

    @Data
    public static class Security{
       private boolean enable;
    }

    @Data
    public static class  Token {
        private Auth auth;
        private String secret;
        private  int expiresIn;
    }

    @Data
    public static class Auth{
        private String path;
    }

    @Data
    public static class Excluded{
        private String path;
    }
}
