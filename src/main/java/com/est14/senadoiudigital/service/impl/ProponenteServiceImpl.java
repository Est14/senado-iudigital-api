package com.est14.senadoiudigital.service.impl;

import com.est14.senadoiudigital.model.Proponente;
import com.est14.senadoiudigital.repo.ProponenteRepo;
import com.est14.senadoiudigital.service.ProponenteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProponenteServiceImpl implements ProponenteService {

    private final ProponenteRepo repo;

    public ProponenteServiceImpl(ProponenteRepo repo) {
        this.repo = repo;
    }

    public Optional<Proponente> getOne(Integer id){
        return repo.findById(id);
    }

    public List<Proponente> getAll(){
        return repo.findAll();
    }

    public Optional<Proponente> create(Proponente proponente){
        return Optional.of(repo.save(proponente));
    }

    public Optional<Proponente> update(Proponente proponente){
        Optional<Proponente> proponente1 = getOne(proponente.getId());

        if(proponente1.isEmpty()){
            return proponente1;
        }
        proponente1.get().setNombre(proponente.getNombre());
        return proponente1;
    }
}
