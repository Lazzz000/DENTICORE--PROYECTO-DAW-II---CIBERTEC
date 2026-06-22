package com.cibertec.denticore.security.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario", schema = "seguridad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, unique = true, length = 15)
    private String dni;

    @Column(name = "tipo_documento_sunat", nullable = false, length = 1)
    @org.hibernate.annotations.JdbcTypeCode(java.sql.Types.CHAR)
    private String tipoDocumentoSunat = "1";


    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(name = "direccion_fiscal", length = 250)
    private String direccionFiscal;

    @Column(length = 100)
    private String correo;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "google_subject_id", length = 255)
    private String googleSubjectId;

    @Column(name = "ruta_foto_perfil", length = 500)
    private String rutaFotoPerfil;

    @Column(nullable = false)
    private Boolean activo = true; // Default en SQL
}