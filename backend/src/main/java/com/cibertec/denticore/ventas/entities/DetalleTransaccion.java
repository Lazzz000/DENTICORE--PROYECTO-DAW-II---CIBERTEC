package com.cibertec.denticore.ventas.entities;

import com.cibertec.denticore.catalogo.entities.ItemCatalogo;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_transaccion", schema = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    private TransaccionComercial transaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item_catalogo", nullable = false)
    private ItemCatalogo itemCatalogo;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_aplicado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioAplicado;

    @Column(name = "numero_pieza")
    private Integer numeroPieza;
}