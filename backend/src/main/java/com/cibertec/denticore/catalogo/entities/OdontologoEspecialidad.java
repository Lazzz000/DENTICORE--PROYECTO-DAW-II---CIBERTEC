package com.cibertec.denticore.catalogo.entities;

import com.cibertec.denticore.security.entities.Odontologo;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "odontologo_especialidad", schema = "catalogo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OdontologoEspecialidad {

    @EmbeddedId
    private OdontologoEspecialidadId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idOdontologo")
    @JoinColumn(name = "id_odontologo")
    private Odontologo odontologo;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEspecialidad")
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OdontologoEspecialidadId implements Serializable {

        @Column(name = "id_odontologo")
        private Integer idOdontologo;

        @Column(name = "id_especialidad")
        private Integer idEspecialidad;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OdontologoEspecialidadId that = (OdontologoEspecialidadId) o;
            return Objects.equals(idOdontologo, that.idOdontologo) &&
                   Objects.equals(idEspecialidad, that.idEspecialidad);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idOdontologo, idEspecialidad);
        }
    }
}
