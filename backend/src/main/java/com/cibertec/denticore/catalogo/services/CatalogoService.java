package com.cibertec.denticore.catalogo.services;

import com.cibertec.denticore.catalogo.dto.request.EspecialidadRequestDTO;
import com.cibertec.denticore.catalogo.dto.request.ItemCatalogoRequestDTO;
import com.cibertec.denticore.catalogo.dto.response.EspecialidadResponseDTO;
import com.cibertec.denticore.catalogo.dto.response.ItemCatalogoResponseDTO;

import java.util.List;

public interface CatalogoService {

    List<EspecialidadResponseDTO> listarEspecialidadesActivas();

    List<ItemCatalogoResponseDTO> listarItemsActivos();

    EspecialidadResponseDTO crearEspecialidad(EspecialidadRequestDTO dto);

    EspecialidadResponseDTO actualizarEspecialidad(Integer id, EspecialidadRequestDTO dto);

    ItemCatalogoResponseDTO crearItem(ItemCatalogoRequestDTO dto);

    ItemCatalogoResponseDTO actualizarItem(Integer id, ItemCatalogoRequestDTO dto);

    void desactivarItem(Integer id);
}
