package com.est14.senadoiudigital.validation;

import com.est14.senadoiudigital.exception.ApiUnauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {

    private static final String CLIENT_CREDENTIAL = "client_credentials";

    public void validate(MultiValueMap<String, String> paramMap, String grandType) {

        // 1. Validamos el grant_type
        if(!grandType.equals(CLIENT_CREDENTIAL)){
            try {
                message("El campo grant_type es invalido");
            } catch (ApiUnauthorized e) {
                throw new RuntimeException(e);
            }
        }

        // 2. Validamos los valores del param
        if(Objects.isNull(paramMap) ||
                Objects.requireNonNull(paramMap.getFirst("client_id")).isEmpty() ||
                Objects.requireNonNull(paramMap.getFirst("client_secret")).isEmpty()  ){

            try {
                message("client_id y/o client_secret no son validos");
            } catch (ApiUnauthorized e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void message(String message) throws ApiUnauthorized {
        throw new ApiUnauthorized(message);
    }
}
