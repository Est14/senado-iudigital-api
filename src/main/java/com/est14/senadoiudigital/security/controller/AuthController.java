package com.est14.senadoiudigital.security.controller;


import com.est14.senadoiudigital.security.dto.JwtDto;
import com.est14.senadoiudigital.security.dto.LoginUser;
import com.est14.senadoiudigital.security.dto.NewUser;
import com.est14.senadoiudigital.security.enums.NameRole;
import com.est14.senadoiudigital.security.jwt.JwtProvider;
import com.est14.senadoiudigital.security.model.Role;
import com.est14.senadoiudigital.security.model.Usuario;
import com.est14.senadoiudigital.security.service.RoleService;
import com.est14.senadoiudigital.security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/senado-iudigital/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody NewUser newUser){

        if(usuarioService.existsUser(newUser.getEmail())){
            return new ResponseEntity<>(
                    "Usaurio " + newUser.getEmail() + " ya existe",
                    HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario(
                newUser.getNombre(),
                newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));

        List<Role> roles = new ArrayList<>();
        Role role = roleService.getRole(NameRole.ROLE_USER).get();
        log.info("Este es el roler de la DB --> " + role);
        roles.add(role);

        usuario.setRoles(roles);
        usuarioService.create(usuario);

        return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUser loginUser){
        log.info("Entro a controller");
        if(!usuarioService.existsUser(loginUser.getEmail())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Antes de Auth");

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));

        log.info("Auth --> " + authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("Auth --> " + userDetails.toString());
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);

    }
}
