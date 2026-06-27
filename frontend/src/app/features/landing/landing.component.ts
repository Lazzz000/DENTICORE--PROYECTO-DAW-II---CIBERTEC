import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="min-h-screen bg-surface-main">
      <header class="bg-white shadow-card">
        <div class="container mx-auto px-6 py-4 flex justify-between items-center">
          <h1 class="text-2xl font-bold text-primary">DENTICORE</h1>
          <a routerLink="/login" class="bg-primary text-white px-6 py-2 rounded-lg hover:bg-primary-hover transition">
            Iniciar Sesión
          </a>
        </div>
      </header>

      <section class="container mx-auto px-6 py-20 text-center">
        <h2 class="text-5xl font-bold text-ink mb-6">Tu Sonrisa, Nuestra Prioridad</h2>
        <p class="text-xl text-ink-muted mb-8">Clínica dental de confianza con tecnología de vanguardia</p>
        <a routerLink="/login" class="inline-block bg-primary text-white px-8 py-3 rounded-lg text-lg font-semibold hover:bg-primary-hover transition">
          Agenda tu Cita
        </a>
      </section>

      <section class="container mx-auto px-6 py-16">
        <div class="grid md:grid-cols-3 gap-8">
          <div class="bg-white p-6 rounded-lg shadow-card">
            <h3 class="text-xl font-semibold text-ink mb-2">Especialistas Certificados</h3>
            <p class="text-ink-muted">Odontólogos con años de experiencia</p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-card">
            <h3 class="text-xl font-semibold text-ink mb-2">Tecnología Moderna</h3>
            <p class="text-ink-muted">Equipos de última generación</p>
          </div>
          <div class="bg-white p-6 rounded-lg shadow-card">
            <h3 class="text-xl font-semibold text-ink mb-2">Atención Personalizada</h3>
            <p class="text-ink-muted">Trato humano y profesional</p>
          </div>
        </div>
      </section>
    </div>
  `
})
export class LandingComponent {}
