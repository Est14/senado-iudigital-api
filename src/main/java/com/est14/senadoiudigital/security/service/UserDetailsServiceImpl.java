package com.est14.senadoiudigital.security.service;


import com.est14.senadoiudigital.security.model.MainUser;
import com.est14.senadoiudigital.security.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getUser(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + "no existe"));

        return MainUser.build(usuario);
    }
}
