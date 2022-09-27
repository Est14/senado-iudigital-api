package com.est14.senadoiudigital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SenadorDto {

    private Integer id;
    private String nombre;
    private Integer activo;
    private Integer Departamento;

}
