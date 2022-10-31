package com.est14.senadoiudigital.security.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewUser {

    private String nombre;
    private String email;
    private String password;
    private List<String> roles = new ArrayList<>();

}
