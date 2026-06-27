# Matriz de Enrutamiento y Seguridad (DentiCore 2.0 Web)

## 1. Estrategia de Carga (Lazy Loading Estricto)
El rendimiento es crítico. Ningún módulo de negocio (`features/portal/` o `features/admin/`) debe empaquetarse en el archivo `main.js` inicial. 
Todo enrutamiento debe utilizar exclusivamente la función `loadComponent` combinada con importaciones dinámicas (`() => import(...)`) propia de la API de Standalone Components de Angular 17+. Queda rotundamente prohibido el uso de la directiva legada `loadChildren` vinculada a `NgModules`.

## 2. Definición de Rutas y Capas de Seguridad (Guards)

### 2.1. Capa Pública (Autenticación)
* **Ruta Base:** `/login`
* **Componente:** `LoginComponent` (`features/auth/login.component.ts`)
* **Regla de Seguridad:** Acceso público. Sin embargo, si el usuario ya posee un token JWT válido en el `AuthService` e intenta navegar explícitamente a `/login`, el sistema debe interceptarlo y redirigirlo a su respectivo panel (Portal o Dashboard) para evitar dobles inicios de sesión.

### 2.2. Front-Office: Portal de Autogestión del Paciente
* **Protección:** `AuthGuard` (Verifica firma y expiración del JWT) + `RoleGuard` (Exige el rol `PACIENTE`).
* **Ruta Base:** `/portal` -> Renderiza el `PortalLayoutComponent` (Sidebar y Topbar con la paleta de colores oficial).
* **Hijos (Children):**
  * `/portal/citas` -> `CitasComponent`: Catálogo de especialidades, búsqueda de disponibilidad y formulario transaccional de reserva.
  * `/portal/historial` -> `HistorialComponent`: Interfaz de solo lectura para el seguimiento de atenciones previas y visualización estática del odontograma.

### 2.3. Back-Office: Gestión Clínica y Administrativa
* **Protección:** `AuthGuard` (Verifica JWT) + `RoleGuard` (Exige los roles `ADMIN` u `ODONTOLOGO`).
* **Ruta Base:** `/admin` -> Renderiza el `AdminLayoutComponent` (Sidebar color `#334155`).
* **Hijos (Children):**
  * `/admin/dashboard` -> `DashboardComponent`: Cards KPI del día y listado rápido de citas.
  * `/admin/pacientes` -> `PacientesListComponent`: Buscador de pacientes (Soporte CRM).
  * `/admin/odontograma/:idCita` -> `OdontogramaEditorComponent`: Editor interactivo (SVG) para registrar hallazgos clínicos. Requiere el ID de la cita en la URL para vincular la atención médica de forma atómica.
  * `/admin/facturacion/:idCita` -> `FacturacionComponent`: Interfaz para procesar ventas y adelantos, cerrando el ciclo comercial.

## 3. Orquestación del "Single Login"
El sistema cuenta con un solo punto de entrada (`/login`) para todos los actores de la clínica. El enrutamiento dinámico ocurre en el bloque de respuesta HTTP del inicio de sesión:
1. El `LoginComponent` envía el `LoginRequest` al BFF.
2. Al recibir el HTTP 200 OK, almacena el `token` de forma segura mediante el `AuthService`.
3. Descodifica el payload del JWT o lee el campo `rol` de la respuesta (`LoginResponse`).
4. **Bifurcación:**
   * Si `rol === 'PACIENTE'`, ejecuta: `router.navigate(['/portal/citas'])`.
   * Si `rol === 'ADMIN' || rol === 'ODONTOLOGO'`, ejecuta: `router.navigate(['/admin/dashboard'])`.

## 4. Implementación en Código (app.routes.ts)
El siguiente bloque define el contrato de rutas oficial que debe regir la aplicación:

```typescript
import { Routes } from '@angular/router';
import { authGuard } from './core/auth/auth.guard';
import { roleGuard } from './core/auth/role.guard';

export const routes: Routes = [
  // 1. Redirección por defecto
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // 2. Módulo Público
  { 
    path: 'login', 
    loadComponent: () => import('./features/auth/login.component').then(m => m.LoginComponent) 
  },

  // 3. Portal de Pacientes
  { 
    path: 'portal',
    canActivate: [authGuard, roleGuard],
    data: { rolesPermitidos: ['PACIENTE'] },
    loadComponent: () => import('./features/portal/portal-layout.component').then(m => m.PortalLayoutComponent),
    children: [
      { path: 'citas', loadComponent: () => import('./features/portal/citas/citas.component').then(m => m.CitasComponent) },
      { path: 'historial', loadComponent: () => import('./features/portal/historial/historial.component').then(m => m.HistorialComponent) },
      { path: '', redirectTo: 'citas', pathMatch: 'full' }
    ]
  },

  // 4. Back-Office Clínico
  { 
    path: 'admin',
    canActivate: [authGuard, roleGuard],
    data: { rolesPermitidos: ['ADMIN', 'ODONTOLOGO'] },
    loadComponent: () => import('./features/admin/admin-layout.component').then(m => m.AdminLayoutComponent),
    children: [
      { path: 'dashboard', loadComponent: () => import('./features/admin/dashboard/dashboard.component').then(m => m.DashboardComponent) },
      { path: 'pacientes', loadComponent: () => import('./features/admin/pacientes-list/pacientes-list.component').then(m => m.PacientesListComponent) },
      { path: 'odontograma/:idCita', loadComponent: () => import('./features/admin/odontograma-editor/odontograma-editor.component').then(m => m.OdontogramaEditorComponent) },
      { path: 'facturacion/:idCita', loadComponent: () => import('./features/admin/facturacion/facturacion.component').then(m => m.FacturacionComponent) },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

  // 5. Fallback - Ruta no encontrada
  { path: '**', redirectTo: 'login' }
];