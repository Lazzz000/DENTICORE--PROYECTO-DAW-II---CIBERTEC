package com.cibertec.denticore.security.repositories;

import com.cibertec.denticore.security.entities.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRol.UsuarioRolId> {
}
