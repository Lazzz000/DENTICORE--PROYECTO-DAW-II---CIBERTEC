package com.cibertec.denticore.security.dto.response;

import lombok.Data;

@Data
public class UsuarioMinimoDTO {

    private Integer id;
    private String dni;
    private String nombres;
    private String apellidos;
}
