# Arquitectura Frontend y Reglas de Desarrollo (DentiCore 2.0 Web)

## 1. Visión General del Sistema

El cliente web de DentiCore 2.0 es una Single Page Application (SPA) desarrollada para operar bajo el patrón Backend For Frontend (BFF). Su propósito es consumir las APIs RESTful de Spring Boot, centralizando la experiencia de usuario tanto para el Front-Office (Pacientes) como para el Back-Office (Odontólogos y Administradores) a través de un ecosistema de Single Login.

## 2. Pila Tecnológica Estricta

El desarrollo se subordina exclusivamente a las siguientes tecnologías. La introducción de librerías de terceros requiere aprobación arquitectónica previa.

- Core Framework Angular 17+ utilizando la API de Standalone Components. Queda estrictamente prohibido el uso del decorador `@NgModule`.
- Motor de Estilos Tailwind CSS v3+. Se prohíbe el uso de CSS en línea o la creación de clases personalizadas en archivos `.css``.scss` para maquetación estructural.
- Componentes de UI Construcción nativa basada en los Design Tokens. Prohibido el uso de librerías de componentes pesadas como Angular Material o Bootstrap que sobreescriban Tailwind.
- Manejo de Formularios Uso exclusivo de ReactiveFormsModule. La lógica de validación (sintaxis, campos requeridos) debe vivir en las clases TypeScript (FormBuilder), nunca en las plantillas HTML.

## 3. Topología de Directorios (Clean Architecture)

El código fuente dentro de `srcapp` debe respetar una separación estricta de responsabilidades.

### 3.1. Directorio `core` (El Cerebro)

Contiene la lógica de negocio pura, configuraciones globales y elementos que se instancian una sola vez (Singletons). Ningún archivo aquí debe contener HTML o CSS.

- coreauth Servicios de autenticación, manejo de sesión y Guards (AuthGuard, RoleGuard).
- coreinterceptors Interceptores HTTP (Inyección de Token JWT, manejo de errores globales 401403500).
- coreservices Servicios de comunicación con el API (ej. CitaService, HistorialService).
- coremodels Interfaces de TypeScript generadas estrictamente desde el `openapi-2.0.1.yaml`.

### 3.2. Directorio `shared` (El Sistema de Diseño)

Contiene elementos visuales y utilitarios agnósticos al contexto de negocio.

- sharedui (Dumb Components) Botones, Modales, Badges de estado, Inputs. Solo reciben datos vía `@Input()` y emiten eventos vía `@Output()`. No inyectan servicios.
- sharedutils Pipes personalizados (ej. formateo de moneda) y Directivas puras.

### 3.3. Directorio `features` (Los Módulos de Negocio)

Contiene los Smart Components organizados por dominio. Estos componentes sí inyectan servicios de `core` y orquestan a los componentes de `shared`. Deben cargarse de forma diferida (Lazy Loading).

- featuresauth Flujo de inicio de sesión unificado.
- featuresportal Autogestión del paciente.
- featuresadmin Gestión clínica, transaccional y dashboard.

###Estructura referencia:
El proyecto se estructurará físicamente bajo la referencia del siguiente árbol. 
```text
DENTICORE- PROYECTO DAW II - CIBERTEC/
├── backend/
├── database/
├── docs/                           # Documentación Maestra (El Contrato)
│   ├── FRONTEND_ARCHITECTURE.md
│   ├── ROUTING_MATRIX.md
│   └── UI_DESIGN_SYSTEM.md
├── frontend/
│   ├── public/                     # Assets estáticos (Imágenes, SVGs puros)
│   ├── src/
│   │   ├── app/
│   │   │   ├── core/               # El Cerebro: Singleton Services, Guards, Models
│   │   │   │   ├── auth/
│   │   │   │   │   ├── auth.service.ts
│   │   │   │   │   ├── auth.guard.ts
│   │   │   │   │   └── role.guard.ts
│   │   │   │   ├── interceptors/
│   │   │   │   │   ├── jwt.interceptor.ts
│   │   │   │   │   └── error.interceptor.ts
│   │   │   │   ├── models/
│   │   │   │   │   └── api.models.ts   # Interfaces exactas del openapi-2.0.1.yaml
│   │   │   │   └── services/
│   │   │   │       ├── cita.service.ts
│   │   │   │       └── historial.service.ts
│   │   │   │
│   │   │   ├── features/           # Smart Components: Módulos de Negocio (Lazy Loaded)
│   │   │   │   ├── admin/          # Back-Office Clínico
│   │   │   │   │   ├── dashboard/
│   │   │   │   │   ├── facturacion/
│   │   │   │   │   ├── odontograma-editor/
│   │   │   │   │   ├── pacientes-list/
│   │   │   │   │   └── admin-layout/   # Layout contenedor (Sidebar + Topbar)
│   │   │   │   ├── auth/           # Punto de entrada unificado
│   │   │   │   │   └── login/
│   │   │   │   └── portal/         # Front-Office Pacientes
│   │   │   │       ├── citas/
│   │   │   │       ├── historial/
│   │   │   │       └── portal-layout/  # Layout contenedor del paciente
│   │   │   │
│   │   │   ├── shared/             # Dumb Components: Sistema de Diseño y UI
│   │   │   │   ├── ui/             # Elementos visuales reutilizables
│   │   │   │   │   ├── button/
│   │   │   │   │   ├── card-kpi/
│   │   │   │   │   ├── modal/
│   │   │   │   │   └── input-field/
│   │   │   │   └── utils/          # Pipes y Directivas puras
│   │   │   │       └── currency.pipe.ts
│   │   │   │
│   │   │   ├── app.component.ts    # Raíz de la SPA (<router-outlet>)
│   │   │   ├── app.config.ts       # Configuración global (Providers, HttpClient, Interceptors)
│   │   │   └── app.routes.ts       # Matriz de Enrutamiento
│   │   │
│   │   ├── environments/
│   │   │   ├── environment.ts      # Prod (BASE_URL = dominio real)
│   │   │   └── environment.development.ts # Dev (BASE_URL = http://localhost:8080/api/v1)
│   │   │
│   │   ├── index.html              # Inyección de tipografía Poppins
│   │   ├── main.ts                 # Archivo de arranque (Bootstrap Standalone)
│   │   └── styles.css              # Directivas de Tailwind (@tailwind base; etc.)
│   │
│   ├── tailwind.config.js          # Design Tokens (Colores Teal, Fuentes)
│   ├── angular.json                # Configuración del CLI
│   └── package.json
└── docker-compose.yml

## 4. Contrato de Datos y API-First Approach

La aplicación Angular no inventa ni muta estructuras de datos por conveniencia visual.

Toda respuesta HTTP debe estar tipada mediante una interface TypeScript que refleje fielmente la especificación del `openapi-2.0.1.yaml`.

Regla Innegociable Está rotundamente prohibido el uso del tipo genérico `any` para mapear respuestas de red o variables de estado de la aplicación.

## 5. Gestión del Estado y Reactividad

Para modernizar el rendimiento y evitar fugas de memoria (Memory Leaks), se separan los paradigmas de reactividad

- Estado de UI (Síncrono) Para controlar el renderizado del DOM (cargando, visibilidad de modales, contadores), se utilizará obligatoriamente el sistema de Signals de Angular (`signal()`, `computed()`, `effect()`).
- Estado de Red (Asíncrono) Para las peticiones HTTP (`HttpClient`), se mantendrá el uso de RxJS (Observables). Las suscripciones en los componentes deben desuscribirse automáticamente (usando el pipe `async` en el HTML o `takeUntilDestroyed()`).

## 6. Seguridad y Manejo de Tokens

- Aislamiento del Storage Ningún componente dentro de `features` tiene permitido invocar `localStorage.getItem()` o `sessionStorage`. Toda lectura, escritura o eliminación del Token JWT se debe delegar a un método centralizado dentro de `coreauthAuthService`.
- Inyección Transparente Las peticiones en los servicios (CitaService, etc.) no deben enviar el token manualmente. El `JwtInterceptor` es el único responsable de clonar la petición HTTP y adjuntar la cabecera `Authorization Bearer token` de forma centralizada.