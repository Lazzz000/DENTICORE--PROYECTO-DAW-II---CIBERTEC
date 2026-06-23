package com.cibertec.denticore.catalogo.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemCatalogoResponseDTO {

    private Integer id;
    private String codigo;
    private String nombre;
    private String tipo;
    private BigDecimal costoReferencial;
    private Integer duracionMinutos;
    private Integer idEspecialidad;
}
