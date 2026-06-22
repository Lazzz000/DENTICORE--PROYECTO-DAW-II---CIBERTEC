package com.cibertec.denticore.clinica.repositories;

import com.cibertec.denticore.clinica.entities.ElementoOdontograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementoOdontogramaRepository extends JpaRepository<ElementoOdontograma, Integer> {
}
