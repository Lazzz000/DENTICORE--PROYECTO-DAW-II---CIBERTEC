import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environments';
import { ProcesarVentaRequest } from '../models/api.model';

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  private readonly apiUrl = `${environment.apiBaseUrl}/ventas`;

  constructor(private readonly http: HttpClient) {}

  procesar(request: ProcesarVentaRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/procesar`, request);
  }
}