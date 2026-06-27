package com.cibertec.denticore.crm.services;

import com.cibertec.denticore.crm.dto.request.CitaRequestDTO;
import com.cibertec.denticore.crm.dto.response.CitaResponseDTO;

public interface CitaService {

    CitaResponseDTO programarCita(CitaRequestDTO dto, String dniUsuarioAutenticado);
}
