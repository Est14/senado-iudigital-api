package com.est14.senadoiudigital.controller;

import com.est14.senadoiudigital.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/senado-iudigital-api")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService service;


}
