package com.cibertec.denticore.catalogo.services;

import com.cibertec.denticore.catalogo.dto.response.EspecialidadResponseDTO;
import com.cibertec.denticore.catalogo.dto.response.ItemCatalogoResponseDTO;
import com.cibertec.denticore.catalogo.entities.Especialidad;
import com.cibertec.denticore.catalogo.entities.ItemCatalogo;
import com.cibertec.denticore.catalogo.repositories.EspecialidadRepository;
import com.cibertec.denticore.catalogo.repositories.ItemCatalogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final EspecialidadRepository especialidadRepository;
    private final ItemCatalogoRepository itemCatalogoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadResponseDTO> listarEspecialidadesActivas() {
        return especialidadRepository.findByActivoTrue().stream()
                .map(this::mapearEspecialidad)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCatalogoResponseDTO> listarItemsActivos() {
        return itemCatalogoRepository.findByActivoTrue().stream()
                .map(this::mapearItemCatalogo)
                .toList();
    }

    private EspecialidadResponseDTO mapearEspecialidad(Especialidad especialidad) {
        EspecialidadResponseDTO dto = new EspecialidadResponseDTO();
        dto.setId(especialidad.getId());
        dto.setNombre(especialidad.getNombre());
        return dto;
    }

    private ItemCatalogoResponseDTO mapearItemCatalogo(ItemCatalogo item) {
        ItemCatalogoResponseDTO dto = new ItemCatalogoResponseDTO();
        dto.setId(item.getId());
        dto.setCodigo(item.getCodigo());
        dto.setNombre(item.getNombre());
        dto.setTipo(item.getTipo());
        dto.setCostoReferencial(item.getCostoReferencial());
        dto.setDuracionMinutos(item.getDuracionMinutos());
        dto.setIdEspecialidad(item.getEspecialidad() != null ? item.getEspecialidad().getId() : null);
        return dto;
    }
}
