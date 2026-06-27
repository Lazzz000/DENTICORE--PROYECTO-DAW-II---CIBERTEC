package com.cibertec.denticore.security.repositories;

import com.cibertec.denticore.security.entities.Usuario;
import com.cibertec.denticore.security.entities.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRol.UsuarioRolId> {

    @Query("SELECT ur FROM UsuarioRol ur JOIN FETCH ur.rol WHERE ur.id.idUsuario = :idUsuario AND ur.activo = true")
    List<UsuarioRol> findByIdUsuario(@Param("idUsuario") Integer idUsuario);
    

    @Query("SELECT ur.usuario FROM UsuarioRol ur WHERE ur.rol.nombre = :nombreRol AND ur.usuario.activo = true AND ur.activo = true")
    List<Usuario> findUsuariosByRolNombreAndActivoTrue(@Param("nombreRol") String nombreRol);
}
