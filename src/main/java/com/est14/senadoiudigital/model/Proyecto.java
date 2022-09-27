package com.est14.senadoiudigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Proyecto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_proyecto")
    private String idProyecto;
    private String nombre;
    private Date fecha;
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Proponente proponente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private EstadoProyecto estadoProyecto;

    @OneToMany(mappedBy = "proyecto")
    private List<Voto> votos;

}
