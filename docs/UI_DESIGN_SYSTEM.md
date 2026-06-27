# Sistema de Diseño y UI (DentiCore 2.0 Web)

## 1. Filosofía Visual y Estética

El diseño de DentiCore 2.0 se inspira en sistemas SaaS modernos de alto rendimiento (Apple, Linear, Notion). Para el sector salud, el objetivo es transmitir: profesionalismo, limpieza absoluta, fricción cero y tecnología.

El diseño debe priorizar el espacio en blanco ("breathing room") y la jerarquía de la información sobre la densidad de datos.

## 2. Paleta de Colores Oficial (Design Tokens)

Está estrictamente prohibido utilizar colores arbitrarios de Tailwind que no estén mapeados en esta paleta. Todo color debe llamarse a través de sus tokens personalizados (ej. `bg-primary`, `text-ink-muted`).

- **Superficies (Surface):**
  - Fondo base de la aplicación: `#F8FAFC` (Slate 50).
  - Fondo de Tarjetas y Modales: `#FFFFFF` (Blanco puro).
  - Bordes y separadores divisorios: `#E2E8F0` (Slate 200).

- **Identidad de Marca (Primary):**
  - Acción principal / Fondo activo: `#0F766E` (Teal 700).
  - Estado Hover: `#115E59` (Teal 800).

- **Textos (Ink):**
  - Títulos y texto principal: `#0F172A` (Slate 900).
  - Subtítulos, labels y placeholders: `#64748B` (Slate 500).

- **Semántica de Estado (Status):**
  - Éxito (Altas, confirmaciones): `#16A34A` (Green 600).
  - Advertencia (Deudas, precauciones): `#F59E0B` (Amber 500).
  - Error (Conflictos, eliminación): `#DC2626` (Red 600).

- **Navegación (Sidebar):**
  - Fondo del menú lateral: `#334155` (Slate 700).

## 3. Tipografía e Iconografía

- **Tipografía Única:** Se utilizará exclusivamente la familia **Poppins**. Pesos permitidos: 300, 400, 500, 600 y 700. Está prohibida la importación o uso de cualquier otra fuente (como Inter o Roboto).

- **Iconografía:** El proyecto utilizará estrictamente **Lucide Icons** (mediante la librería `lucide-angular`). Queda prohibido el uso de FontAwesome, Material Symbols o carga de SVGs en línea que no pertenezcan al ecosistema de Lucide.

## 4. Configuración Maestra de Tailwind (`tailwind.config.js`)

El motor de estilos de Angular debe reflejar la paleta oficial. El siguiente objeto de configuración es inmutable:

```javascript
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        primary: { DEFAULT: '#0F766E', hover: '#115E59' },
        sidebar: '#334155',
        surface: {
          main: '#F8FAFC',
          card: '#FFFFFF',
          border: '#E2E8F0',
          soft: '#ECFEFF'
        },
        ink: {
          DEFAULT: '#0F172A',
          muted: '#64748B'
        },
        status: {
          success: '#16A34A',
          warning: '#F59E0B',
          error: '#DC2626'
        }
      },
      fontFamily: {
        sans: ['Poppins', 'sans-serif']
      },
      boxShadow: {
        'card': '0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px -1px rgba(0, 0, 0, 0.1)'
      }
    },
  },
  plugins: [require('@tailwindcss/forms')],
}
```

## 5. Topología HTML de Componentes

Para garantizar la modularidad en Angular y evitar layouts rotos, las vistas de tipo Feature deben estructurarse bajo esta jerarquía en el DOM:

- Sidebar (Componente Fijo, anidado en el Layout).
- Topbar (Componente Fijo, solo con borde inferior, sin sombras pesadas).
- Header (Título de la vista y acciones primarias, ej. "Nueva Cita").
- Cards KPI (Métricas superiores, opcional).
- Filtros (Inputs de búsqueda).
- Contenido Principal (Tablas o Grillas).

## 6. Reglas de Renderizado y Animación

- **Animaciones Permitidas:** Para no saturar el DOM ni afectar el rendimiento, solo se permiten transiciones CSS nativas: hover, transition, shadow y un ligero scale en botones.

- **Responsive Design:** La estrategia es Desktop-First para el Back-Office clínico. Prioridad: Desktop -> Tablet -> Mobile.

- **Bordes:** Las tarjetas (Cards) y modales siempre usarán esquinas redondeadas (`rounded-xl` o `rounded-2xl`). Quedan prohibidas las esquinas cuadradas.