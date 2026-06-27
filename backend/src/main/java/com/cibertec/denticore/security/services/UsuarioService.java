package com.cibertec.denticore.security.services;

import com.cibertec.denticore.security.dto.response.UsuarioMinimoDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioMinimoDTO> listarOdontologosActivos();

    List<UsuarioMinimoDTO> listarPacientesActivos();
}
