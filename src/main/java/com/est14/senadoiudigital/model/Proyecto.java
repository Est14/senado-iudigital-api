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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    private String nombre;
    private Date fecha;
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "idEstado")
    private Estado estado;

    @ManyToOne()
    @JoinColumn(name = "idProponente")
    private Proponente proponente;


    @OneToMany(mappedBy = "proyecto")
    private List<Voto> votos;

}
