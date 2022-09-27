package com.est14.senadoiudigital.repo;

import com.est14.senadoiudigital.model.Senador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SenadorRepo extends JpaRepository<Senador, Integer> {

    @Query("SELECT s FROM Senador s WHERE s.partido.idPartido=?1")
    List<Senador> findByIdPartido(Integer IdPartido);
}
