package com.cibertec.denticore.catalogo.services;

import com.cibertec.denticore.catalogo.dto.request.EspecialidadRequestDTO;
import com.cibertec.denticore.catalogo.dto.request.ItemCatalogoRequestDTO;
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

    @Override
    @Transactional
    public EspecialidadResponseDTO crearEspecialidad(EspecialidadRequestDTO dto) {
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(dto.getNombre());
        especialidad.setActivo(true);

        Especialidad guardada = especialidadRepository.save(especialidad);
        return mapearEspecialidad(guardada);
    }

    @Override
    @Transactional
    public EspecialidadResponseDTO actualizarEspecialidad(Integer id, EspecialidadRequestDTO dto) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        especialidad.setNombre(dto.getNombre());

        Especialidad actualizada = especialidadRepository.save(especialidad);
        return mapearEspecialidad(actualizada);
    }

    @Override
    @Transactional
    public ItemCatalogoResponseDTO crearItem(ItemCatalogoRequestDTO dto) {
        ItemCatalogo item = new ItemCatalogo();
        item.setCodigo(dto.getCodigo());
        item.setNombre(dto.getNombre());
        item.setTipo(dto.getTipo());
        item.setCostoReferencial(dto.getCostoReferencial());
        item.setDuracionMinutos(dto.getDuracionMinutos());
        item.setActivo(true);
        item.setMostrarEnWeb(true);
        item.setTipoAfectacionId("10");
        item.setEspecialidad(buscarEspecialidad(dto.getIdEspecialidad()));

        ItemCatalogo guardado = itemCatalogoRepository.save(item);
        return mapearItemCatalogo(guardado);
    }

    @Override
    @Transactional
    public ItemCatalogoResponseDTO actualizarItem(Integer id, ItemCatalogoRequestDTO dto) {
        ItemCatalogo item = itemCatalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        item.setCodigo(dto.getCodigo());
        item.setNombre(dto.getNombre());
        item.setTipo(dto.getTipo());
        item.setCostoReferencial(dto.getCostoReferencial());
        item.setDuracionMinutos(dto.getDuracionMinutos());
        item.setEspecialidad(buscarEspecialidad(dto.getIdEspecialidad()));

        ItemCatalogo actualizado = itemCatalogoRepository.save(item);
        return mapearItemCatalogo(actualizado);
    }

    @Override
    @Transactional
    public void desactivarItem(Integer id) {
        ItemCatalogo item = itemCatalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        item.setActivo(false);
        itemCatalogoRepository.save(item);
    }

    private Especialidad buscarEspecialidad(Integer idEspecialidad) {
        if (idEspecialidad == null) {
            return null;
        }
        return especialidadRepository.findById(idEspecialidad)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
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
