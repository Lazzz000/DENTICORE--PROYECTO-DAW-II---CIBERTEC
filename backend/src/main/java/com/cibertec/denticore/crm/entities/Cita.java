package com.cibertec.denticore.crm.entities;

import com.cibertec.denticore.crm.enums.EstadoCita;
import com.cibertec.denticore.security.entities.Odontologo;
import com.cibertec.denticore.security.entities.Paciente;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita", schema = "crm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_odontologo", nullable = false)
    private Odontologo odontologo;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCita estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lead_contacto")
    private LeadContacto leadContacto;

    @Column(name = "canal_origen", nullable = false, length = 50)
    private String canalOrigen;

    @Column(name = "monto_adelanto", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoAdelanto = BigDecimal.ZERO;

    @Column(name = "referencia_adelanto", length = 100)
    private String referenciaAdelanto;
}
