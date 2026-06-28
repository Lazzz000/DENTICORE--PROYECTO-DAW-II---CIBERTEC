import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { OdontogramaService } from '../../../core/services/odontograma.sevice';
import { DetalleHistorial, RegistrarAtencionRequest } from '../../../core/models/api.model';

@Component({
  selector: 'app-odontograma',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './odontograma.component.html'
})
export class OdontogramaComponent {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly odontogramaService = inject(OdontogramaService);

  idCita = Number(
  this.route.snapshot.paramMap.get('idCita') ??
  this.route.snapshot.paramMap.get('id')
);
  idPaciente = Number(this.route.snapshot.queryParamMap.get('idPaciente'));
  paciente = this.route.snapshot.queryParamMap.get('paciente') ?? 'Paciente no identificado';
  dni = this.route.snapshot.queryParamMap.get('dni') ?? '-';

  historial = signal<DetalleHistorial[]>([]);
  cargando = signal(false);
  guardando = signal(false);
  error = signal('');
  exito = signal('');

  request: RegistrarAtencionRequest = {
    idCita: this.idCita,
    motivoConsulta: 'Dolor dental',
    notasClinicas: '',
    tipoOdontograma: 'Inicial',
    detalles: [
      {
        idElementoClinico: 1,
        numeroPieza: 36,
        diagnostico: 'Caries moderada',
        estadoTratamiento: 'Pendiente'
      }
    ]
  };

  ngOnInit(): void {
    this.cargarHistorial();
  }

  cargarHistorial(): void {
    if (!this.idPaciente) {
      return;
    }

    this.cargando.set(true);

    this.odontogramaService.obtenerHistorialPaciente(this.idPaciente).subscribe({
      next: (data) => {
        this.historial.set(data);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('No se pudo cargar el historial clínico.');
        this.cargando.set(false);
      }
    });
  }

  agregarDetalle(): void {
    this.request.detalles.push({
      idElementoClinico: 1,
      numeroPieza: 0,
      diagnostico: '',
      estadoTratamiento: 'Pendiente'
    });
  }

  eliminarDetalle(index: number): void {
    this.request.detalles.splice(index, 1);
  }

  guardarAtencion(): void {
    this.error.set('');
    this.exito.set('');

    if (!this.request.motivoConsulta.trim()) {
      this.error.set('Ingresa el motivo de consulta.');
      return;
    }

    if (this.request.detalles.length === 0) {
      this.error.set('Agrega al menos un detalle clínico.');
      return;
    }

    this.guardando.set(true);

    this.odontogramaService.registrarAtencion(this.request).subscribe({
      next: () => {
        this.guardando.set(false);
        this.exito.set('Atención registrada correctamente.');
        this.router.navigate(['/admin/citas']);
      },
      error: () => {
        this.guardando.set(false);
        this.error.set('No se pudo guardar la atención. Verifica que la cita esté EN_CURSO.');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/admin/citas']);
  }
}