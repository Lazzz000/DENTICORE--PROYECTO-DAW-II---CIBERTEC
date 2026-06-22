package com.cibertec.denticore.clinica.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_odontograma", schema = "clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleOdontograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_odontograma", nullable = false)
    private Odontograma odontograma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_elemento_clinico", nullable = false)
    private ElementoOdontograma elementoClinico;

    @Column(name = "numero_pieza", nullable = false)
    private Integer numeroPieza;

    @Column(nullable = false, length = 255)
    private String diagnostico;

    @Column(name = "estado_tratamiento", nullable = false, length = 50)
    private String estadoTratamiento;
}