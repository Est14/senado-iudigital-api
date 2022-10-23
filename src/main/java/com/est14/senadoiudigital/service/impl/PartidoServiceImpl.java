package com.est14.senadoiudigital.service.impl;

import com.est14.senadoiudigital.model.Partido;
import com.est14.senadoiudigital.repo.PartidoRepo;
import com.est14.senadoiudigital.service.PartidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepo partidoRepo;

    public PartidoServiceImpl(PartidoRepo partidoRepo) {
        this.partidoRepo = partidoRepo;
    }

    public Optional<Partido> getOne(Integer id){
        return partidoRepo.findById(id);
    }

    public List<Partido> getAll(){
        return partidoRepo.findAll();
    }

    public Optional<Partido> create(Partido partido){
        return Optional.of(partidoRepo.save(partido));
    }

    public Optional<Partido> update(Partido partido){
        Optional<Partido> partido1 = getOne(partido.getIdPartido());

        if(partido1.isEmpty()){
            return partido1;
        }
        partido1.get().setNombre(partido.getNombre());
        return partido1;
    }
}
