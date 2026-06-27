package com.cibertec.denticore.crm.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CitaRequestDTO {

    private Integer idPaciente;
    private Integer idOdontologo;
    private LocalDateTime fechaHora;
    private String canalOrigen;
    private BigDecimal montoAdelanto;
    private String referenciaAdelanto;
}
