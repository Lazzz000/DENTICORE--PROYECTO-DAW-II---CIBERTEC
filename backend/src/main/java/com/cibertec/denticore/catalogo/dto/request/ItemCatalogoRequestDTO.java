package com.cibertec.denticore.catalogo.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemCatalogoRequestDTO {

    private String codigo;
    private String nombre;
    private String tipo;
    private BigDecimal costoReferencial;
    private Integer duracionMinutos;
    private Integer idEspecialidad;
}
