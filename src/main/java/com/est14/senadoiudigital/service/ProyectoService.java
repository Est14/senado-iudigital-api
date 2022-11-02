package com.est14.senadoiudigital.service;

import com.est14.senadoiudigital.dto.ProyectoDto;
import com.est14.senadoiudigital.model.Proyecto;

import java.util.List;
import java.util.Optional;

public interface ProyectoService  {


    Optional<Proyecto> getOne(Integer id);
    List<Proyecto> getAll();
    void create(Integer idProponente, ProyectoDto proyectoDto);
    void update(Integer idproyecto, ProyectoDto proyectoDto);

    void delete(Integer id);

    void votarProyecto(int idProyecto);

}
