# Documento de Arquitectura de Software (SAD)

**Proyecto:** DENTICORE-PROYECTO DAWII-CIBERTEC
**Versión:** 1.0

## 1. Visión General de la Arquitectura
DentiCore 2.0 utiliza una arquitectura orientada a microservicios simulados (Microservices-Ready) utilizando el patrón de base de datos compartida (Shared Database)[cite: 8]. Implementa un patrón Backend For Frontend (BFF) diseñado para alta disponibilidad y resiliencia[cite: 8]. Todo el ecosistema operativo está contenerizado[cite: 1].

## 2. Definición de Componentes Estructurales
La arquitectura se divide estrictamente en los siguientes cinco componentes, los cuales están mapeados directamente con las etiquetas de gestión del tablero ágil (Jira):

### 2.1. UI-Angular (Capa de Presentación)
*   **Tecnología:** Angular 17+, Tailwind CSS[cite: 8].
*   **Responsabilidad:** Single Page Application (SPA) totalmente desacoplada[cite: 8]. Maneja el enrutamiento del lado del cliente, la renderización gráfica del odontograma interactivo (SVG) y la interceptación de peticiones HTTP para inyectar tokens de autorización JWT[cite: 8].

### 2.2. API-Gateway / Auth (Capa de Entrada y Seguridad)
*   **Tecnología:** Spring Cloud Gateway, Spring Security, JWT, BCrypt[cite: 1, 8].
*   **Responsabilidad:** Actúa como punto de entrada único hacia el backend (Edge Server)[cite: 8]. Gestiona la autenticación de usuarios (Pacientes y Odontólogos), valida los tokens JWT y cifra las credenciales en la base de datos de forma unidireccional usando `BCryptPasswordEncoder`[cite: 8].

### 2.3. Business-Core / Messaging (Capa de Negocio y Eventos)
*   **Tecnología:** Spring Boot, Spring Web, Spring Data JPA, RabbitMQ / Kafka[cite: 1, 8].
*   **Responsabilidad:** Contiene la lógica de negocio central (Catálogo, Citas, Transacciones comerciales)[cite: 8]. Utiliza la anotación `@Transactional` para garantizar operaciones ACID[cite: 8]. Emite y consume eventos asíncronos (ej. `CitaCreadaEvent`) hacia el Message Broker para delegar procesos no bloqueantes, como el envío de notificaciones por correo[cite: 8].

### 2.4. Database / JPA (Capa de Persistencia)
*   **Tecnología:** PostgreSQL, Hibernate[cite: 8].
*   **Responsabilidad:** Base de datos única compartida, segregada lógicamente por esquemas (ej. `seguridad`, `catalogo`, `clinica`, `ventas`) para aislar los dominios[cite: 8]. Spring Data JPA gestiona el mapeo ORM, eliminando la dependencia técnica de los antiguos procedimientos almacenados[cite: 8].

### 2.5. Infrastructure / Docker (Capa de Orquestación y Resiliencia)
*   **Tecnología:** Docker, Docker Compose, Resilience4j, Spring Boot Actuator[cite: 1, 8].
*   **Responsabilidad:** Contenerización y despliegue local del ecosistema completo mediante `docker-compose.yml`[cite: 1, 8]. Expone métricas de salud (`/actuator/health`) y aplica patrones de tolerancia a fallos (Circuit Breaker) para evitar la caída en cascada de los servicios, garantizando la resiliencia exigida por la arquitectura[cite: 1, 8].