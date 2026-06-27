package com.cibertec.denticore.security.services;

import com.cibertec.denticore.security.dto.response.UsuarioMinimoDTO;
import com.cibertec.denticore.security.entities.Usuario;
import com.cibertec.denticore.security.repositories.UsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRolRepository usuarioRolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioMinimoDTO> listarOdontologosActivos() {
        return usuarioRolRepository.findUsuariosByRolNombreAndActivoTrue("ODONTOLOGO").stream()
                .map(this::mapearUsuarioMinimo)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioMinimoDTO> listarPacientesActivos() {
        return usuarioRolRepository.findUsuariosByRolNombreAndActivoTrue("PACIENTE").stream()
                .map(this::mapearUsuarioMinimo)
                .toList();
    }

    private UsuarioMinimoDTO mapearUsuarioMinimo(Usuario usuario) {
        UsuarioMinimoDTO dto = new UsuarioMinimoDTO();
        dto.setId(usuario.getId());
        dto.setDni(usuario.getDni());
        dto.setNombres(usuario.getNombres());
        dto.setApellidos(usuario.getApellidos());
        return dto;
    }
}
