package com.est14.senadoiudigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "estado_proyecto")
public class EstadoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idestado_proyecto")
    private Integer id;
    private String estado;

    @OneToMany(mappedBy = "estadoProyecto")
    private List<Proyecto> proyectos;
}
