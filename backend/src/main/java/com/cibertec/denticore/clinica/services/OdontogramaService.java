package com.cibertec.denticore.clinica.services;

import com.cibertec.denticore.clinica.dto.request.GuardarOdontogramaRequestDTO;
import com.cibertec.denticore.clinica.dto.response.DetalleHistorialDTO;

import java.util.List;

public interface OdontogramaService {

    void registrarAtencionClinica(GuardarOdontogramaRequestDTO dto, String dniAutenticado);

    List<DetalleHistorialDTO> obtenerHistorialPaciente(Integer idPaciente);
}
