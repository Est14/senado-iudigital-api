package com.est14.senadoiudigital.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idUsuario;
    private String nombre;
    private String email;
    private String contrasena;



}
