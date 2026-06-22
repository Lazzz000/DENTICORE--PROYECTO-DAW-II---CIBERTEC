package com.cibertec.denticore.security.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "odontologo", schema = "seguridad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Odontologo {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(nullable = false, unique = true, length = 20)
    private String cop;

    @Column(name = "firma_digital", length = 500)
    private String firmaDigital;

    @Column(nullable = false)
    private Boolean activo;
}