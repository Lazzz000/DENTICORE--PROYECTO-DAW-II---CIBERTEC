package com.cibertec.denticore.clinica.repositories;

import com.cibertec.denticore.clinica.entities.AtencionClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtencionClinicaRepository extends JpaRepository<AtencionClinica, Integer> {}
