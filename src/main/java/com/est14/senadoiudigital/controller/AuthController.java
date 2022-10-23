package com.est14.senadoiudigital.controller;

import com.est14.senadoiudigital.service.impl.AuthService;
import com.est14.senadoiudigital.validation.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/senado-iudigital/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthValidator authValidator;

    @PostMapping(value = "/oauth/client_credential/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(
            @RequestBody MultiValueMap<String, String> paramMap,
            @RequestParam("grant_type") String grandType){

        authValidator.validate(paramMap, grandType);

        return ResponseEntity.ok(authService.login(
                paramMap.getFirst("client_id"),
                paramMap.getFirst("client_secret")));
    }
}
