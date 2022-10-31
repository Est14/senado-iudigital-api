package com.est14.senadoiudigital.security.service;


import com.est14.senadoiudigital.security.model.Usuario;
import com.est14.senadoiudigital.security.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UsuarioService  {

    private final UsuarioRepo usuarioRepo;

    public Optional<Usuario> getUser(String email){
        log.info("Entro a service get user");
        return usuarioRepo.findByEmail(email);
    }

    public boolean existsUser(String email){
        log.info("Entro a services exists user");
        return usuarioRepo.existsByEmail(email);
    }

    public void create(Usuario usuario){
       usuarioRepo.save(usuario);
    }


}
