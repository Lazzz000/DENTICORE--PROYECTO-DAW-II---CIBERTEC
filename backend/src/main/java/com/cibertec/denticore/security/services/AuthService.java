package com.cibertec.denticore.security.services;

import com.cibertec.denticore.security.dto.request.RegistroPacienteRequestDTO;

public interface AuthService {

    void registrarPaciente(RegistroPacienteRequestDTO dto);
}
