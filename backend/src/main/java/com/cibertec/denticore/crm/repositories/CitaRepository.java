package com.cibertec.denticore.crm.repositories;

import com.cibertec.denticore.crm.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cita c " +
           "WHERE c.odontologo.idUsuario = :idOdontologo " +
           "AND c.fechaHora = :fechaHora " +
           "AND c.estado IN ('PENDIENTE', 'CONFIRMADA', 'EN_SALA', 'EN_CURSO')")
    boolean existsByOdontologoAndFechaHoraAndEstadoOcupado(
            @Param("idOdontologo") Integer idOdontologo,
            @Param("fechaHora") LocalDateTime fechaHora);
}
