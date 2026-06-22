package com.cibertec.denticore.catalogo.repositories;

import com.cibertec.denticore.catalogo.entities.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
}