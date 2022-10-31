package com.est14.senadoiudigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFound extends Exception{

    public UsuarioNotFound(String message) {
        super(message);
    }
}
