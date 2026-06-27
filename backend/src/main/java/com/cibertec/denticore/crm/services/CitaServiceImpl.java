package com.cibertec.denticore.crm.services;

import com.cibertec.denticore.crm.dto.request.CitaRequestDTO;
import com.cibertec.denticore.crm.dto.response.CitaResponseDTO;
import com.cibertec.denticore.crm.entities.Cita;
import com.cibertec.denticore.crm.enums.EstadoCita;
import com.cibertec.denticore.crm.repositories.CitaRepository;
import com.cibertec.denticore.security.entities.Odontologo;
import com.cibertec.denticore.security.entities.Paciente;
import com.cibertec.denticore.security.repositories.OdontologoRepository;
import com.cibertec.denticore.security.repositories.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;

    @Override
    @Transactional
    public CitaResponseDTO programarCita(CitaRequestDTO dto, String dniUsuarioAutenticado) {
        boolean existeColision = citaRepository.existsByOdontologoAndFechaHoraAndEstadoOcupado(
                dto.getIdOdontologo(), dto.getFechaHora());

        if (existeColision) {
            throw new IllegalStateException("El odontólogo ya tiene una cita reservada en este horario.");
        }

        Paciente paciente = obtenerPaciente(dto, dniUsuarioAutenticado);

        Odontologo odontologo = odontologoRepository.findById(dto.getIdOdontologo())
                .orElseThrow(() -> new RuntimeException("Odontólogo no encontrado"));

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setOdontologo(odontologo);
        cita.setFechaHora(dto.getFechaHora());
        cita.setEstado(EstadoCita.PENDIENTE);
        cita.setCanalOrigen(dto.getCanalOrigen());
        cita.setMontoAdelanto(dto.getMontoAdelanto() != null ? dto.getMontoAdelanto() : BigDecimal.ZERO);
        cita.setReferenciaAdelanto(dto.getReferenciaAdelanto());

        Cita guardada = citaRepository.save(cita);

        return mapearRespuesta(guardada, "Cita programada correctamente");
    }

    private Paciente obtenerPaciente(CitaRequestDTO dto, String dniUsuarioAutenticado) {
        if (dto.getIdPaciente() != null) {
            return pacienteRepository.findById(dto.getIdPaciente())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        }

        return pacienteRepository.findByUsuarioDni(dniUsuarioAutenticado)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado para el usuario autenticado"));
    }

    private CitaResponseDTO mapearRespuesta(Cita cita, String mensaje) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(cita.getId());
        dto.setEstado(cita.getEstado().name());
        dto.setFechaHora(cita.getFechaHora());
        dto.setMensaje(mensaje);
        return dto;
    }
}
