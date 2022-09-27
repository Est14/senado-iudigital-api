package com.est14.senadoiudigital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer id;
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private List<Senador> senadors;
}
