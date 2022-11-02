package com.est14.senadoiudigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voto")
    private Integer id;
    private String voto;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;

    @ManyToOne()
    @JoinColumn(name = "idSenador")
    private Senador senador;

}
