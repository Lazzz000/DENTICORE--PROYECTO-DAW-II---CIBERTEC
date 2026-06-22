package com.cibertec.denticore.catalogo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.math.BigDecimal;
import java.sql.Types;

@Entity
@Table(name = "item_catalogo", schema = "catalogo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(name = "costo_referencial", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoReferencial;

    @Column(name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;

    @Column(name = "tipo_afectacion_id", nullable = false, length = 2)
    @JdbcTypeCode(Types.CHAR)
    private String tipoAfectacionId = "10";

    @Column(name = "mostrar_en_web", nullable = false)
    private Boolean mostrarEnWeb = true;

    @Column(nullable = false)
    private Boolean activo = true; // Sincronizado con DEFAULT TRUE del SQL
}