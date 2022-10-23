package com.est14.senadoiudigital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Integer idUsuario;
    private String nombre;
    private String email;
    private String role;

}
