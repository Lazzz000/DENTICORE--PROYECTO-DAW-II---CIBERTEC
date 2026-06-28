import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CitaService } from '../../../core/services/cita.service';

type ItemVentaSimulado = {
  descripcion: string;
  cantidad: number;
  precio: number;
};

@Component({
  selector: 'app-facturacion',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './facturacion.component.html'
})
export class FacturacionComponent {

  private readonly citaService = inject(CitaService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  idCita = Number(
    this.route.snapshot.paramMap.get('idCita') ??
    this.route.snapshot.paramMap.get('id')
  );

  idPaciente = Number(this.route.snapshot.queryParamMap.get('idPaciente'));
  paciente = this.route.snapshot.queryParamMap.get('paciente') ?? 'Paciente no identificado';
  dni = this.route.snapshot.queryParamMap.get('dni') ?? '-';

  metodoPago = 'Efectivo';
  montoPagado = 0;
  pagado = signal(false);

  items: ItemVentaSimulado[] = [
    {
      descripcion: 'Consulta odontológica',
      cantidad: 1,
      precio: 50
    },
    {
      descripcion: 'Tratamiento registrado en odontograma',
      cantidad: 1,
      precio: 120
    }
  ];

  total(): number {
    return this.items.reduce((acc, item) => acc + item.cantidad * item.precio, 0);
  }

  saldo(): number {
    return Math.max(this.total() - Number(this.montoPagado || 0), 0);
  }

      registrarPago(): void {
      const venta = {
        idCita: this.idCita,
        idPaciente: this.idPaciente,
        paciente: this.paciente,
        dni: this.dni,
        metodoPago: this.metodoPago,
        montoPagado: this.montoPagado,
        total: this.total(),
        saldo: this.saldo(),
        items: this.items,
        fecha: new Date().toISOString()
      };

      const ventas = JSON.parse(localStorage.getItem('ventas_simuladas') ?? '[]');
      ventas.push(venta);

      localStorage.setItem('ventas_simuladas', JSON.stringify(ventas));

      this.citaService.actualizarEstado(this.idCita, 'FINALIZADA').subscribe({
        next: () => {
          this.pagado.set(true);
          this.router.navigate(['/admin/citas']);
        },
        error: () => {
          this.pagado.set(true);
          alert('Pago guardado localmente, pero no se pudo finalizar la cita.');
        }
      });
    }

  volver(): void {
    this.router.navigate(['/admin/citas']);
  }
}