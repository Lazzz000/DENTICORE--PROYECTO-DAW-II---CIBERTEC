package com.cibertec.denticore.catalogo.controllers;

import com.cibertec.denticore.catalogo.dto.response.EspecialidadResponseDTO;
import com.cibertec.denticore.catalogo.dto.response.ItemCatalogoResponseDTO;
import com.cibertec.denticore.catalogo.services.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
@RequiredArgsConstructor
public class CatalogoApiController {

    private final CatalogoService catalogoService;

    @GetMapping("/especialidades")
    public ResponseEntity<List<EspecialidadResponseDTO>> listarEspecialidades() {
        return ResponseEntity.ok(catalogoService.listarEspecialidadesActivas());
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemCatalogoResponseDTO>> listarItems() {
        return ResponseEntity.ok(catalogoService.listarItemsActivos());
    }
}
