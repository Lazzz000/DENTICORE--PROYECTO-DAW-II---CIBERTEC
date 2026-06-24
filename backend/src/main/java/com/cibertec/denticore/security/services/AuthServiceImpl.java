package com.cibertec.denticore.security.services;

import com.cibertec.denticore.clinica.entities.HistoriaClinica;
import com.cibertec.denticore.clinica.repositories.HistoriaClinicaRepository;
import com.cibertec.denticore.security.dto.request.RegistroPacienteRequestDTO;
import com.cibertec.denticore.security.entities.Paciente;
import com.cibertec.denticore.security.entities.Rol;
import com.cibertec.denticore.security.entities.Usuario;
import com.cibertec.denticore.security.entities.UsuarioRol;
import com.cibertec.denticore.security.repositories.PacienteRepository;
import com.cibertec.denticore.security.repositories.RolRepository;
import com.cibertec.denticore.security.repositories.UsuarioRepository;
import com.cibertec.denticore.security.repositories.UsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final PacienteRepository pacienteRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registrarPaciente(RegistroPacienteRequestDTO dto) {
        if (usuarioRepository.findByDni(dto.getDni()).isPresent()) {
            throw new RuntimeException("El DNI ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getDni());
        usuario.setDni(dto.getDni());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidos(dto.getApellidos());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTipoDocumentoSunat("1");
        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        usuario.setActivo(true);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Rol rolPaciente = rolRepository.findByNombre("PACIENTE")
                .orElseThrow(() -> new RuntimeException("Rol PACIENTE no encontrado"));

        UsuarioRol.UsuarioRolId usuarioRolId = new UsuarioRol.UsuarioRolId();
        usuarioRolId.setIdUsuario(usuarioGuardado.getId());
        usuarioRolId.setIdRol(rolPaciente.getId());

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setId(usuarioRolId);
        usuarioRol.setUsuario(usuarioGuardado);
        usuarioRol.setRol(rolPaciente);
        usuarioRol.setActivo(true);

        usuarioRolRepository.save(usuarioRol);

        Paciente paciente = new Paciente();
        paciente.setUsuario(usuarioGuardado);
        paciente.setGrupoSanguineo(dto.getGrupoSanguineo());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setActivo(true);

        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setPaciente(pacienteGuardado);
        historiaClinica.setCodigoHistorial("HC-" + dto.getDni());
        historiaClinica.setCreadoPor(usuarioGuardado);

        historiaClinicaRepository.save(historiaClinica);
    }
}
