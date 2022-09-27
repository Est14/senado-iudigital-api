package com.est14.senadoiudigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Integer id;
    private String nombre;
    private Date creacion;
    private Integer activo;

    @OneToMany(mappedBy = "partido")
    private List<Senador> senadors;
}
