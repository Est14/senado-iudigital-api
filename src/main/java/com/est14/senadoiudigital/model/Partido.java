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
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Integer idPartido;
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creacion;
    private Integer activo;
    private String imagen;

    @OneToMany(mappedBy = "partido", fetch = FetchType.LAZY)
    private List<Senador> senadors;
}
