import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  Calendar,
  CheckCircle,
  Clock,
  CreditCard,
  DollarSign,
  FileText,
  LucideAngularModule,
  ShoppingCart,
  Stethoscope,
  TrendingUp,
  UserPlus,
  Users
} from 'lucide-angular';

import { UsuarioService } from '../../../core/services/usuario.service';
import { CatalogoService } from '../../../core/services/catalogo.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent {
  private readonly usuarioService = inject(UsuarioService);
  private readonly catalogoService = inject(CatalogoService);

  readonly Users = Users;
  readonly Calendar = Calendar;
  readonly Stethoscope = Stethoscope;
  readonly DollarSign = DollarSign;
  readonly TrendingUp = TrendingUp;
  readonly Clock = Clock;
  readonly CheckCircle = CheckCircle;
  readonly UserPlus = UserPlus;
  readonly CreditCard = CreditCard;
  readonly FileText = FileText;
  readonly ShoppingCart = ShoppingCart;

  cargando = signal(false);

  totalPacientes = signal(0);
  totalOdontologos = signal(0);
  totalEspecialidades = signal(0);
  totalItems = signal(0);

  citasHoyFake = signal(18);
  atencionesFake = signal(14);
  ingresosFake = signal(8450);

  actividades = signal([
    {
      titulo: 'Nuevo paciente registrado',
      descripcion: 'Carlos Mendoza ha sido ingresado al sistema clínico.',
      tiempo: 'Hace 5 min',
      tipo: 'paciente'
    },
    {
      titulo: 'Cita confirmada',
      descripcion: 'Dra. Ana Ríos • Profilaxis para Laura Restrepo.',
      tiempo: 'Hace 12 min',
      tipo: 'cita'
    },
    {
      titulo: 'Pago recibido',
      descripcion: 'Monto: S/ 120.00 de Pedro Gómez.',
      tiempo: 'Hace 45 min',
      tipo: 'pago'
    }
  ]);

  citasDelDia = signal([
    {
      hora: '09:00 AM',
      paciente: 'Andrés Silva',
      detalle: 'Dr. Torres • Control',
      estado: 'Atendido',
      estadoClase: 'bg-green-50 text-status-success border-green-200'
    },
    {
      hora: '10:30 AM',
      paciente: 'María Delgado',
      detalle: 'Dra. Ríos • Diseño',
      estado: 'En espera',
      estadoClase: 'bg-orange-50 text-status-warning border-orange-200'
    },
    {
      hora: '03:15 PM',
      paciente: 'Roberto Díaz',
      detalle: 'Dr. Torres • Cirugía',
      estado: 'Pendiente',
      estadoClase: 'bg-gray-100 text-gray-600 border-gray-200'
    }
  ]);

  ngOnInit(): void {
    this.cargarDashboard();
  }

  cargarDashboard(): void {
    this.cargando.set(true);

    this.usuarioService.listarPacientesActivos().subscribe({
      next: (data) => this.totalPacientes.set(data.length),
      error: () => this.totalPacientes.set(1248)
    });

    this.usuarioService.listarOdontologosActivos().subscribe({
      next: (data) => this.totalOdontologos.set(data.length),
      error: () => this.totalOdontologos.set(6)
    });

    this.catalogoService.listarEspecialidades().subscribe({
      next: (data) => this.totalEspecialidades.set(data.length),
      error: () => this.totalEspecialidades.set(8)
    });

    this.catalogoService.listarItems().subscribe({
      next: (data) => {
        this.totalItems.set(data.length);
        this.cargando.set(false);
      },
      error: () => {
        this.totalItems.set(15);
        this.cargando.set(false);
      }
    });
  }
}