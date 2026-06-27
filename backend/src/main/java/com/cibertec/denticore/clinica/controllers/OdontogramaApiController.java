package com.cibertec.denticore.clinica.controllers;

import com.cibertec.denticore.clinica.dto.request.GuardarOdontogramaRequestDTO;
import com.cibertec.denticore.clinica.services.OdontogramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/clinica/odontograma")
@RequiredArgsConstructor
public class OdontogramaApiController {

    private final OdontogramaService odontogramaService;

    @PostMapping
    public ResponseEntity<Void> registrarAtencion(
            @RequestBody GuardarOdontogramaRequestDTO dto,
            Principal principal) {
        String dniAutenticado = principal.getName();
        odontogramaService.registrarAtencionClinica(dto, dniAutenticado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
