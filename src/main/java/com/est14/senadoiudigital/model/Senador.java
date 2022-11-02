package com.est14.senadoiudigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Senador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_senador")
    private Integer idSenador;
    private String nombre;
    private Integer activo;

    @OneToMany(mappedBy = "senador", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Voto> votos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "idPartido")
    private Partido partido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;
}
