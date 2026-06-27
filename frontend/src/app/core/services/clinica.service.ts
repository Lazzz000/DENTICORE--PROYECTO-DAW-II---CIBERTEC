import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environments';
import { GuardarOdontogramaRequest } from '../models/api.model';

@Injectable({
  providedIn: 'root'
})
export class ClinicaService {
  private readonly apiUrl = `${environment.apiBaseUrl}/clinica`;

  constructor(private readonly http: HttpClient) {}

  guardarOdontograma(request: GuardarOdontogramaRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/odontograma`, request);
  }
}