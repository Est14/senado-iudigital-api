package com.est14.senadoiudigital.security.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class MainUser implements UserDetails {


    private String nombre;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public MainUser(String nombre, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Recibimos un usuairo con roles y convertimos a un usermain con authorities
    public static MainUser build(Usuario usuario){
        List<GrantedAuthority> authorities1 =
                usuario.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(
                                role.getNameRole().name()
                        ))
                        .collect(Collectors.toList());

        return new MainUser(usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), authorities1);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre(){
        return nombre;
    }
}
