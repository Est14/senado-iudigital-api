package com.est14.senadoiudigital.controller;


import com.est14.senadoiudigital.dto.SenadorDto;
import com.est14.senadoiudigital.model.Departamento;
import com.est14.senadoiudigital.model.Partido;
import com.est14.senadoiudigital.model.Senador;
import com.est14.senadoiudigital.repo.DepartamentoRepo;
import com.est14.senadoiudigital.service.PartidoService;
import com.est14.senadoiudigital.service.SenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/senado-iudigital/api")
public class PartidoController {

    private final PartidoService service;
    private final SenadorService senadorService;
    private final DepartamentoRepo departamentoRepo;


    @Autowired
    public PartidoController(PartidoService service, SenadorService senadorService, DepartamentoRepo departamentoRepo) {
        this.service = service;
        this.senadorService = senadorService;
        this.departamentoRepo = departamentoRepo;
    }

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

    @PostMapping("/partidos")
    public ResponseEntity<Partido> create(@RequestBody Partido partido){

        partido.setCreacion(new Date());
        partido.setActivo(1);

        return service.create(partido)
                .map(partido1 -> new ResponseEntity<>(partido,  HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/partidos/{id}")
    public void delete(@PathVariable int id){
        Optional<Partido> partido = service.getOne(id);

        if (partido.isPresent()){
            partido.get().setActivo(0);
            service.create(partido.get());
        }
    }

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

    @PostMapping("/partidos/{id}/senadores")
    public ResponseEntity<Senador> create(@PathVariable int id, @RequestBody SenadorDto senadorDTO){

        Optional<Departamento> departamento = departamentoRepo.findById(senadorDTO.getDepartamento());
        Optional<Partido> partido = service.getOne(id);

        if (departamento.isPresent() && partido.isPresent()){
            Senador senador = new Senador();
            senador.setNombre(senadorDTO.getNombre());
            senador.setActivo(1);
            senador.setPartido(partido.get());
            senador.setDepartamento(departamento.get());
            return senadorService.create(senador)
                    .map(senador1 -> new ResponseEntity<>(senador1, HttpStatus.ACCEPTED))
                    .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
