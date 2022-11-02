package com.est14.senadoiudigital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEstado;
    private String estado;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Proyecto> proyectos;


}
