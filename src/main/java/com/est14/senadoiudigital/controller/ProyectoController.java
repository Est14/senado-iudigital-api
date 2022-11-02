package com.est14.senadoiudigital.controller;

import com.est14.senadoiudigital.dto.ProyectoDto;
import com.est14.senadoiudigital.model.Proyecto;
import com.est14.senadoiudigital.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/senado-iudigital/api")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> getProyectos(){
        return new ResponseEntity<>(proyectoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> getProyecto(@PathVariable Integer id){

        Proyecto proyecto = proyectoService.getOne(id).orElseThrow();
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/proponente/{id}/proyectos")
    public void create(@PathVariable int id, @RequestBody ProyectoDto proyecto){
        proyectoService.create(id, proyecto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/proyectos/{id}")
    public void update(@PathVariable Integer id, @RequestBody ProyectoDto proyectoDto){

        proyectoService.update(id, proyectoDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/proyectos/{id}")
    public void delete(@PathVariable Integer id){

        Proyecto proyecto = proyectoService.getOne(id).orElseThrow();
        proyectoService.delete(proyecto.getIdProyecto());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/proyectos/{id}/votar")
    public void votar(@PathVariable Integer id){

        proyectoService.votarProyecto(id);
    }
}
