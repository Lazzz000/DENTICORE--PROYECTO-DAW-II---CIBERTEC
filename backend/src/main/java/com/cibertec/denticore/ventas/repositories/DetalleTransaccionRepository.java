package com.cibertec.denticore.ventas.repositories;

import com.cibertec.denticore.ventas.entities.DetalleTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleTransaccionRepository extends JpaRepository<DetalleTransaccion, Integer> {
}