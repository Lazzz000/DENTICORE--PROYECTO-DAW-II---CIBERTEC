import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environments';
import { AgendarCitaRequest } from '../models/api.model';

@Injectable({
  providedIn: 'root'
})
export class CitaService {
  private readonly apiUrl = `${environment.apiBaseUrl}/citas`;

  constructor(private readonly http: HttpClient) {}

  agendar(request: AgendarCitaRequest): Observable<void> {
    return this.http.post<void>(this.apiUrl, request);
  }
}