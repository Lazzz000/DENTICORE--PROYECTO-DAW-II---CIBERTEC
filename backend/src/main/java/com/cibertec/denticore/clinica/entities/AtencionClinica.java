package com.cibertec.denticore.clinica.entities;

import com.cibertec.denticore.crm.entities.Cita;
import com.cibertec.denticore.security.entities.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "atencion_clinica", schema = "clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtencionClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historia_clinica", nullable = false)
    private HistoriaClinica historiaClinica;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita", unique = true)
    private Cita cita;

    @Column(name = "motivo_consulta", nullable = false, columnDefinition = "TEXT")
    private String motivoConsulta;

    @Column(name = "notas_clinicas", columnDefinition = "TEXT")
    private String notasClinicas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por", nullable = false)
    private Usuario creadoPor;

    @Column(name = "fecha_atencion", nullable = false)
    private LocalDateTime fechaAtencion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}