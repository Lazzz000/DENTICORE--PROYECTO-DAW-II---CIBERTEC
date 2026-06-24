package com.cibertec.denticore.catalogo.controllers;

import com.cibertec.denticore.catalogo.dto.request.EspecialidadRequestDTO;
import com.cibertec.denticore.catalogo.dto.request.ItemCatalogoRequestDTO;
import com.cibertec.denticore.catalogo.dto.response.EspecialidadResponseDTO;
import com.cibertec.denticore.catalogo.dto.response.ItemCatalogoResponseDTO;
import com.cibertec.denticore.catalogo.services.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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

    @PostMapping("/especialidades")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EspecialidadResponseDTO> crearEspecialidad(@RequestBody EspecialidadRequestDTO dto) {
        EspecialidadResponseDTO creada = catalogoService.crearEspecialidad(dto);
        URI location = URI.create("/catalogo/especialidades/" + creada.getId());
        return ResponseEntity.created(location).body(creada);
    }

    @PutMapping("/especialidades/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<EspecialidadResponseDTO> actualizarEspecialidad(
            @PathVariable Integer id,
            @RequestBody EspecialidadRequestDTO dto) {
        return ResponseEntity.ok(catalogoService.actualizarEspecialidad(id, dto));
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ItemCatalogoResponseDTO> crearItem(@RequestBody ItemCatalogoRequestDTO dto) {
        ItemCatalogoResponseDTO creado = catalogoService.crearItem(dto);
        URI location = URI.create("/catalogo/items/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ItemCatalogoResponseDTO> actualizarItem(
            @PathVariable Integer id,
            @RequestBody ItemCatalogoRequestDTO dto) {
        return ResponseEntity.ok(catalogoService.actualizarItem(id, dto));
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> desactivarItem(@PathVariable Integer id) {
        catalogoService.desactivarItem(id);
        return ResponseEntity.noContent().build();
    }
}
