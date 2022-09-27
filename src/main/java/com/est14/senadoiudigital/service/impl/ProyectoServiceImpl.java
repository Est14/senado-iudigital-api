package com.est14.senadoiudigital.service.impl;

import com.est14.senadoiudigital.model.Proyecto;
import com.est14.senadoiudigital.repo.ProyectoRepo;
import com.est14.senadoiudigital.service.ProyectoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepo repo;

    public ProyectoServiceImpl(ProyectoRepo repo) {
        this.repo = repo;
    }

    public Optional<Proyecto> getOne(String id){
        return repo.findById(id);
    }

    public List<Proyecto> getAll(){
        return repo.findAll();
    }

    public Optional<Proyecto> create(Proyecto proyecto){
        return Optional.of(repo.save(proyecto));
    }

    public Optional<Proyecto> update(Proyecto proyecto){
        Optional<Proyecto> proyecto1 = getOne(proyecto.getIdProyecto());

        if(proyecto1.isEmpty()){
            return proyecto1;
        }
        proyecto1.get().setNombre(proyecto.getNombre());
        return proyecto1;
    }
}
