import { Routes } from '@angular/router';

export const routes: Routes = [
  { 
    path: '', 
    loadComponent: () => import('./features/landing/landing.component').then(m => m.LandingComponent) 
  },
  { 
    path: 'login', 
    loadComponent: () => import('./features/auth/login.component').then(m => m.LoginComponent) 
  },
  { 
    path: 'portal',
    loadComponent: () => import('./features/portal/portal-layout.component').then(m => m.PortalLayoutComponent)
  },
  { 
    path: 'admin',
    loadComponent: () => import('./features/admin/admin-layout.component').then(m => m.AdminLayoutComponent)
  },
  { 
    path: '**', 
    redirectTo: '' 
  }
];
