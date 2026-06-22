package com.cibertec.denticore.ventas.entities;

import com.cibertec.denticore.crm.entities.Cita;
import com.cibertec.denticore.security.entities.Paciente;
import com.cibertec.denticore.security.entities.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaccion_comercial", schema = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita")
    private Cita cita;

    //relación añadida según script SQL (creado_por)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por", nullable = false)
    private Usuario creadoPor;

    @Column(name = "sub_total", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal igv = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "monto_pagado", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal montoPagado = BigDecimal.ZERO;

    // Delegamos el cálculo a PostgreSQL
    @Column(name = "saldo_pendiente", precision = 10, scale = 2, insertable = false, updatable = false)
    private BigDecimal saldoPendiente;

    @Column(name = "metodo_pago", nullable = false, length = 50)
    @Builder.Default
    private String metodoPago = "Efectivo";

    @Column(name = "external_payment_id", length = 100)
    private String externalPaymentId;

    @Column(name = "estado_pago", length = 20)
    private String estadoPago;

    @Column(name = "estado_sunat", nullable = false, length = 50)
    @Builder.Default
    private String estadoSunat = "PENDIENTE";

    @Column(name = "tipo_documento", nullable = false, length = 20)
    private String tipoDocumento;

    @Column(nullable = false, length = 4)
    @JdbcTypeCode(Types.CHAR)
    private String serie = "F001";

    @Column(nullable = false)
    private Integer correlativo;

    @Column(name = "fecha_emision", nullable = false, updatable = false)
    private LocalDateTime fechaEmision = LocalDateTime.now();

    @Column(name = "codigo_hash", length = 100)
    private String codigoHash;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @OneToMany(mappedBy = "transaccion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DetalleTransaccion> detalles = new ArrayList<>();
}