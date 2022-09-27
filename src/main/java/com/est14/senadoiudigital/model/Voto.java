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





    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Senador senador;

}
