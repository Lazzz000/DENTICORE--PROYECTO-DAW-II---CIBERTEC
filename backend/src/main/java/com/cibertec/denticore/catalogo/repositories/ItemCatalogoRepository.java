package com.cibertec.denticore.catalogo.repositories;

import com.cibertec.denticore.catalogo.entities.ItemCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCatalogoRepository extends JpaRepository<ItemCatalogo, Integer> {
    // Método autogenerado para listar ítems por su estado (activo o inactivo)
    List<ItemCatalogo> findByActivoTrue();
}