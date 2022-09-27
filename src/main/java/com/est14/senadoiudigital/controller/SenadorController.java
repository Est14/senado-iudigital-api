package com.est14.senadoiudigital.controller;


import com.est14.senadoiudigital.repo.DepartamentoRepo;
import com.est14.senadoiudigital.service.PartidoService;
import com.est14.senadoiudigital.service.SenadorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/senado-iudigital-api")
public class SenadorController {

    private final SenadorService senadorService;
    private final PartidoService partidoService;
    private final DepartamentoRepo departamentoRepo;

    public SenadorController(SenadorService senadorService, PartidoService partidoService, DepartamentoRepo departamentoRepo) {
        this.senadorService = senadorService;
        this.partidoService = partidoService;
        this.departamentoRepo = departamentoRepo;
    }






}
