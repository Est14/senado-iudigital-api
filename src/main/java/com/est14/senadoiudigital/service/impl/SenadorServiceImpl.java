package com.est14.senadoiudigital.service.impl;


import com.est14.senadoiudigital.model.Senador;
import com.est14.senadoiudigital.repo.SenadorRepo;
import com.est14.senadoiudigital.service.SenadorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SenadorServiceImpl implements SenadorService {

    private final SenadorRepo repo;

    public SenadorServiceImpl(SenadorRepo repo) {
        this.repo = repo;
    }

    public Optional<Senador> getOne(Integer id){
        return repo.findById(id);
    }

    public List<Senador> getAll(){
        return repo.findAll();
    }

    public Optional<Senador> create(Senador senador){
        return Optional.of(repo.save(senador));
    }

    public Optional<Senador> update(Senador senador){
        Optional<Senador> senador1 = getOne(senador.getIdSenador());

        if(senador1.isEmpty()){
            return senador1;
        }
        senador1.get().setNombre(senador.getNombre());
        return senador1;
    }

    @Override
    public List<Senador> findByPartido(int id) {
        return repo.findByIdPartido(id);
    }
}
