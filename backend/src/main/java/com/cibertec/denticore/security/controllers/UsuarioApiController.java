package com.cibertec.denticore.security.controllers;

import com.cibertec.denticore.security.dto.response.UsuarioMinimoDTO;
import com.cibertec.denticore.security.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioApiController {

    private final UsuarioService usuarioService;

    @GetMapping("/odontologos/activos")
    public ResponseEntity<List<UsuarioMinimoDTO>> listarOdontologosActivos() {
        return ResponseEntity.ok(usuarioService.listarOdontologosActivos());
    }

    @GetMapping("/pacientes/activos")
    public ResponseEntity<List<UsuarioMinimoDTO>> listarPacientesActivos() {
        return ResponseEntity.ok(usuarioService.listarPacientesActivos());
    }
}
