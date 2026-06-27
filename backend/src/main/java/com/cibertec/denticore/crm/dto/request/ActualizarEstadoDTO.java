package com.cibertec.denticore.crm.dto.request;

import com.cibertec.denticore.crm.enums.EstadoCita;
import lombok.Data;

@Data
public class ActualizarEstadoDTO {

    private EstadoCita estado;
}
