package com.est14.senadoiudigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Proponente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proponente")
    private Integer id;
    private String nombre;

    @OneToMany(mappedBy = "proponente")
    private List<Proyecto> proyectos;
}
