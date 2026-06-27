package com.cibertec.denticore.crm.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CitaResponseDTO {

    private Integer id;
    private String estado;
    private LocalDateTime fechaHora;
    private String mensaje;
}
