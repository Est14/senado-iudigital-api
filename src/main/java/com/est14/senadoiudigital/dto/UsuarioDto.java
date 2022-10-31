package com.est14.senadoiudigital.dto;

import com.est14.senadoiudigital.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Integer idUsuario;
    private String nombre;
    private String email;
    private List<Role> role;

}
