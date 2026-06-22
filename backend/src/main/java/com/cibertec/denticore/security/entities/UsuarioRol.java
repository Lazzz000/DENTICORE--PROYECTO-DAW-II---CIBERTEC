package com.cibertec.denticore.security.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuario_rol", schema = "seguridad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRol {

    @EmbeddedId
    private UsuarioRolId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Column(nullable = false)
    private Boolean activo = true;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsuarioRolId implements Serializable {
        @Column(name = "id_usuario")
        private Integer idUsuario;

        @Column(name = "id_rol")
        private Integer idRol;

        // Obligatorio sobrescribir equals y hashCode en Embeddable Keys
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UsuarioRolId that = (UsuarioRolId) o;
            return Objects.equals(idUsuario, that.idUsuario) &&
                   Objects.equals(idRol, that.idRol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idUsuario, idRol);
        }
    }
}