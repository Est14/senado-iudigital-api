package com.est14.senadoiudigital.controller;

import com.est14.senadoiudigital.service.ProyectoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/senado-iudigital-api")
public class ProyectoController {

    private ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }


}
