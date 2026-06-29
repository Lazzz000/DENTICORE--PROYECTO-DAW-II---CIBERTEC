# DentiCore 🦷
**Proyecto DAW II - Cibertec | Sistema Integral de Gestión
Odontológica**
DentiCore es una plataforma web completa diseñada para modernizar y
optimizar la gestión de clínicas dentales. Este proyecto integra
tanto el flujo de atención clínica (historias clínicas,
odontogramas interactivos) como la administración del negocio (CRM,
control de citas, facturación y gestión de catálogos).
---
## 🚀 Características Principales
* **Seguridad y Accesos (Identity & Access Management):**
Autenticación y autorización basada en tokens (JWT) con soporte
para múltiples roles (Administrador, Odontólogo, Paciente).
* **Gestión Clínica (Odontograma):** Registro detallado de la
historia clínica del paciente y un odontograma interactivo para
registrar hallazgos y tratamientos por pieza dental.
* **CRM y Citas:** Portal para agendamiento de citas, control de
estados (Pendiente, Confirmada, Atendida) y gestión de leads.
* **Facturación y Ventas:** Procesamiento de transacciones
comerciales, generación de detalles de venta y facturación
vinculada a los tratamientos realizados.
* **Catálogos y Especialidades:** Mantenimiento de las
especialidades odontológicas, tarifas y el catálogo general de
servicios.
* **Dashboard Administrativo:** Panel de control centralizado para
la visión general del rendimiento y operaciones de la clínica.
## ️ Stack Tecnológico
El proyecto está construido bajo una arquitectura cliente-servidor
(Frontend SPA + Backend RESTful API).
### Backend
* **Framework:** Java / Spring Boot
* **Seguridad:** Spring Security + JWT

* **Gestión de Datos:** Spring Data JPA / Hibernate
* **Construcción:** Maven
### Frontend
* **Framework:** Angular
* **Estilos y UI:** Tailwind CSS
* **Lenguaje:** TypeScript
### Infraestructura y Datos
* **Base de Datos:** Motor SQL relacional (Esquema definido en
`/database/init/DATABASE_SCHEMA.sql`)
* **Despliegue:** Contenedores con Docker y Docker Compose
* **API Specs:** Documentación con OpenAPI / Swagger (Ubicado en
`/docs/openapi.yaml`)
---
## 📁 Estructura del Proyecto
El repositorio está organizado en las siguientes carpetas
principales:
```text
/
├── backend/ # Código fuente del API REST en Spring Boot
├── frontend/ # Código fuente de la aplicación cliente en
Angular
├── database/ # Scripts de inicialización y esquemas SQL
├── docs/ # Documentación técnica, diagramas
(Arquitectura, BPMN, DER), PRD y UI Design
└── docker-compose.yml# Orquestación de contenedores para
despliegue local
```

---
## ⚙️ Requisitos Previos
Asegúrate de tener instalado lo siguiente en tu entorno local antes
de iniciar:
* [Java JDK
17](https://www.oracle.com/java/technologies/javase/jdk17-archive-d
ownloads.html) o superior.
* [Node.js y npm](https://nodejs.org/) (Versión recomendada para
Angular).
* [Docker y Docker Compose](https://www.docker.com/) (Recomendado
para un despliegue rápido).

* Maven (Opcional, incluido en el wrapper del proyecto `mvnw`).
---
## 🚀 Instalación y Despliegue
### Opción 1: Despliegue rápido con Docker (Recomendado)
La forma más sencilla de levantar todo el ecosistema (Base de
Datos, Backend y Frontend) es utilizando Docker Compose.
1. Clona el repositorio.
2. Abre una terminal en la raíz del proyecto.
3. Ejecuta el siguiente comando:
```bash
docker-compose up -d --build
```
4. Los servicios estarán disponibles en los puertos configurados en
el `docker-compose.yml`.
### Opción 2: Ejecución Manual
Si deseas ejecutar los servicios por separado para desarrollo:
**1. Base de Datos:**
* Asegúrate de tener un servidor de base de datos corriendo.
* Ejecuta el script `/database/init/DATABASE_SCHEMA.sql` para crear
las tablas necesarias.
* Configura las credenciales en
`backend/src/main/resources/application.yaml`.
**2. Backend (Spring Boot):**
```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```
*El API estará disponible normalmente en `http://localhost:8080`.*
**3. Frontend (Angular):**
```bash
cd frontend
npm install
npm start
```
*La aplicación web estará disponible en `http://localhost:4200`.*
---

## 📚 Documentación
El proyecto cuenta con una documentación técnica exhaustiva en la
carpeta `/docs`, que incluye:
* **Arquitectura:** `ARCHITECTURE.md` y diagramas de arquitectura.
* **Reglas de Negocio:** `REGLAS_NEGOCIO.md`.
* **Diseño de UI:** `UI_DESIGN_SYSTEM.md`.
* **Diagramas:** Modelos Entidad-Relación (DER), Diagramas BPMN
(AS-IS y TO-BE), Diagramas de Secuencia y Casos de Uso.
* **API:** Especificaciones en formato `openapi.yaml`.
---
## 👥 Autores y Créditos
Este sistema ha sido desarrollado como parte del **Proyecto
Integrador DAW II** en **Cibertec**.
-Carlos Lazo 
-Roberth Contreras
