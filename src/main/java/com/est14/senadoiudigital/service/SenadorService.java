package com.est14.senadoiudigital.service;

import com.est14.senadoiudigital.model.Senador;

import java.util.List;
import java.util.Optional;

public interface SenadorService {

    Optional<Senador> getOne(Integer id);
    List<Senador> getAll();
    Optional<Senador> create(Senador senador);
    Optional<Senador> update(Senador senador);

    List<Senador> findByPartido(int id);
}
