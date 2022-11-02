package com.est14.senadoiudigital.repo;

import com.est14.senadoiudigital.model.Proyecto;
import com.est14.senadoiudigital.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepo extends JpaRepository<Voto, Integer> {

    List<Voto> findByProyecto(Proyecto proyecto);
}
