package com.est14.senadoiudigital.repo;

import com.est14.senadoiudigital.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}
