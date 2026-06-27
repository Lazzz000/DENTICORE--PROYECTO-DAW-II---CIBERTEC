package com.cibertec.denticore.clinica.services;

import com.cibertec.denticore.clinica.dto.request.GuardarOdontogramaRequestDTO;

public interface OdontogramaService {

    void registrarAtencionClinica(GuardarOdontogramaRequestDTO dto, String dniAutenticado);
}
