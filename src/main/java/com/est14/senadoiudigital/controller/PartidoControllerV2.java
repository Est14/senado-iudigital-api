package com.est14.senadoiudigital.controller;


import com.est14.senadoiudigital.dto.SenadorDto;
import com.est14.senadoiudigital.model.Departamento;
import com.est14.senadoiudigital.model.Partido;
import com.est14.senadoiudigital.model.Senador;
import com.est14.senadoiudigital.repo.DepartamentoRepo;
import com.est14.senadoiudigital.service.PartidoService;
import com.est14.senadoiudigital.service.SenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/senado-iudigital/api/v2")
@RequiredArgsConstructor
public class PartidoControllerV2 {

    private final PartidoService service;
    private final SenadorService senadorService;
    private final DepartamentoRepo departamentoRepo;


    @GetMapping("/partidos")
    public ResponseEntity<List<Partido>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/partidos/{id}")
    public ResponseEntity<Partido> getOne(@PathVariable int id){
        return service.getOne(id)
                .map(partido -> new ResponseEntity<>(partido, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Start Senators
    @GetMapping("/partidos/{id}/senadores")
    public ResponseEntity<List<Senador>> getSenadores(@PathVariable int id){
        return new ResponseEntity<>(senadorService.findByPartido(id), HttpStatus.OK);
    }

    @GetMapping("/partidos/{id}/senadores/{idSenador}")
    public ResponseEntity<Senador> getSenador(@PathVariable int id, @PathVariable("idSenador") int idSenador){
        List<Senador> senadors = senadorService.findByPartido(id);

        return senadors.stream()
                .filter(senador -> senador.getIdSenador().equals(idSenador))
                .findFirst()
                .map(senador -> new ResponseEntity<>(senador, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
