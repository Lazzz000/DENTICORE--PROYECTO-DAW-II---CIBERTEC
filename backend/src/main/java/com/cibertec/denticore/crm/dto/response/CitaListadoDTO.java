package com.cibertec.denticore.crm.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CitaListadoDTO {

    private Integer idCita;
    private LocalDateTime fechaHora;
    private Integer idPaciente;
    private String pacienteNombreCompleto;
    private String pacienteDni;
    private Integer idOdontologo;
    private String odontologoNombre;
    private String estado;
    private BigDecimal montoAdelanto;
}
