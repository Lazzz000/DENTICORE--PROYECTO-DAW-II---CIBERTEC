import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Calendar, Clock, LucideAngularModule, RefreshCcw, Search, UserCheck } from 'lucide-angular';
import { AuthService } from '../../../core/services/auth.service';
import { CitaService } from '../../../core/services/cita.service';
import { CitaListadoDTO, EstadoCita } from '../../../core/models/api.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './citas.component.html'
})
export class CitasComponent {

    private readonly router = inject(Router);

    private readonly authService = inject(AuthService);
    rolActual = this.authService.rol;   
  private readonly citaService = inject(CitaService);

  readonly Calendar = Calendar;
  readonly Clock = Clock;
  readonly UserCheck = UserCheck;
  readonly RefreshCcw = RefreshCcw;
  readonly Search = Search;



  citas = signal<CitaListadoDTO[]>([]);
  cargando = signal(false);
  error = signal('');

  ngOnInit(): void {
    this.listarCitas();
  }

  listarCitas(): void {
    this.cargando.set(true);
    this.error.set('');

    const request$ = this.rolActual() === 'ODONTOLOGO'
    ? this.citaService.listarMisCitas(undefined, 0, 50)
    : this.citaService.listarAgendaDiaria(undefined, 0, 50);

    request$.subscribe({
      next: (response) => {
        const citasFiltradas = this.rolActual() === 'ODONTOLOGO'
        ? response.content.filter(cita => cita.estado === 'EN_SALA')
        : response.content;

        this.citas.set(citasFiltradas);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('No se pudo cargar la agenda de citas.');
        this.cargando.set(false);
      }
    });
  }

    atenderCita(idCita: number): void {
    this.citaService.actualizarEstado(idCita, 'EN_CURSO').subscribe({
        next: () => this.router.navigate(['/admin/odontograma', idCita]),
        error: () => this.error.set('No se pudo iniciar la atención.')
    });
    }

  cambiarEstado(idCita: number, estado: EstadoCita): void {
    this.citaService.actualizarEstado(idCita, estado).subscribe({
      next: () => this.listarCitas(),
      error: () => this.error.set('No se pudo actualizar el estado de la cita.')
    });
  }

  totalPorEstado(estado: EstadoCita): number {
    return this.citas().filter(cita => cita.estado === estado).length;
  }

  claseEstado(estado: EstadoCita): string {
  const clases: Record<EstadoCita, string> = {
    PENDIENTE: 'bg-orange-50 text-status-warning border-orange-200',
    CONFIRMADA: 'bg-cyan-50 text-primary border-cyan-200',
    EN_SALA: 'bg-amber-50 text-status-warning border-amber-200',
    EN_CURSO: 'bg-purple-50 text-purple-600 border-purple-200',
    ATENDIDA: 'bg-green-50 text-status-success border-green-200',
    FINALIZADA: 'bg-slate-100 text-slate-600 border-slate-200',
    CANCELADA: 'bg-red-50 text-status-error border-red-200'
  };

  return clases[estado];
}

    textoEstado(estado: EstadoCita): string {
      const textos: Record<EstadoCita, string> = {
        PENDIENTE: 'Pendiente',
        CONFIRMADA: 'Confirmada',
        EN_SALA: 'En sala',
        EN_CURSO: 'En curso',
        ATENDIDA: 'Atendida',
        FINALIZADA: 'Finalizada',
        CANCELADA: 'Cancelada'
      };

      return textos[estado];
    }
}