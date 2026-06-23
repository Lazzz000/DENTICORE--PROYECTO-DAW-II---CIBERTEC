package com.cibertec.denticore.catalogo.services;

import com.cibertec.denticore.catalogo.dto.response.EspecialidadResponseDTO;
import com.cibertec.denticore.catalogo.dto.response.ItemCatalogoResponseDTO;

import java.util.List;

public interface CatalogoService {

    List<EspecialidadResponseDTO> listarEspecialidadesActivas();

    List<ItemCatalogoResponseDTO> listarItemsActivos();
}
