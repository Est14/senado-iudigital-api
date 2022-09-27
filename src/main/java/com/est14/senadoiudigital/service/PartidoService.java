package com.est14.senadoiudigital.service;

import com.est14.senadoiudigital.model.Partido;
import com.est14.senadoiudigital.repo.PartidoRepo;

import java.util.List;
import java.util.Optional;

public interface PartidoService {



    public Optional<Partido> getOne(Integer id);

    public List<Partido> getAll();

    public Optional<Partido> create(Partido partido);

    public Optional<Partido> update(Partido partido);
}
