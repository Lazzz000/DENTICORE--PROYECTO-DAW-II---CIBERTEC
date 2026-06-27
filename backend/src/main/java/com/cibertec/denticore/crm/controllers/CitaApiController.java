package com.cibertec.denticore.crm.controllers;

import com.cibertec.denticore.crm.dto.request.CitaRequestDTO;
import com.cibertec.denticore.crm.dto.response.CitaResponseDTO;
import com.cibertec.denticore.crm.services.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/citas")
@RequiredArgsConstructor
public class CitaApiController {

    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaResponseDTO> programarCita(
            @RequestBody CitaRequestDTO dto,
            Principal principal) {
        String dniUsuarioAutenticado = principal.getName();
        CitaResponseDTO response = citaService.programarCita(dto, dniUsuarioAutenticado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<CitaResponseDTO> handleIllegalStateException(IllegalStateException ex) {
        CitaResponseDTO errorResponse = new CitaResponseDTO();
        errorResponse.setMensaje(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
