import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environments';
import { OdontologoActivo, PacienteActivo } from '../models/api.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private readonly apiUrl = `${environment.apiBaseUrl}/usuarios`;

  constructor(private readonly http: HttpClient) {}

  listarPacientesActivos(): Observable<PacienteActivo[]> {
    return this.http.get<PacienteActivo[]>(`${this.apiUrl}/pacientes/activos`);
  }

  listarOdontologosActivos(): Observable<OdontologoActivo[]> {
    return this.http.get<OdontologoActivo[]>(`${this.apiUrl}/odontologos/activos`);
  }
}