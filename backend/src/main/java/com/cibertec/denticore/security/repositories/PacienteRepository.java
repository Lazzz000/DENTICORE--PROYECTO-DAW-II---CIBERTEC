package com.cibertec.denticore.security.repositories;

import com.cibertec.denticore.security.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("SELECT p FROM Paciente p WHERE p.usuario.dni = :dni")
    Optional<Paciente> findByUsuarioDni(@Param("dni") String dni);
}
