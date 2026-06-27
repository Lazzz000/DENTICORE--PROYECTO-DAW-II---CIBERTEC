import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environments';
import { Especialidad , ItemCatalogo } from '../models/api.model';


@Injectable({
  providedIn: 'root'
})
export class CatalogoService {
  private readonly apiUrl = `${environment.apiBaseUrl}/catalogo`;

  constructor(private readonly http: HttpClient) {}

  listarEspecialidades(): Observable<Especialidad[]> {
    return this.http.get<Especialidad[]>(`${this.apiUrl}/especialidades`);
  }

  listarItems(): Observable<ItemCatalogo[]> {
  return this.http.get<ItemCatalogo[]>(`${this.apiUrl}/items`);
  }

}