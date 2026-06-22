package com.cibertec.denticore.clinica.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "elemento_odontograma", schema = "clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElementoOdontograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(name = "aplica_a", nullable = false, length = 50)
    private String aplicaA;

    @Column(name = "color_hex", nullable = false, length = 10)
    private String colorHex;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
