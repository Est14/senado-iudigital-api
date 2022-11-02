package com.est14.senadoiudigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer idProponente;
    private String nombre;

    @OneToMany(mappedBy = "proponente", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Proyecto> proyectos;
}
