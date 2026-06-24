package com.cibertec.denticore.security.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegistroPacienteRequestDTO {

    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String grupoSanguineo;
    private LocalDate fechaNacimiento;
}
