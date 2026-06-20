# Product Requirements Document (PRD)

**Proyecto:** DENTICORE-PROYECTO DAWII-CIBERTEC
**Versión:** 1.0

## 1. Propósito del Documento
Este documento define los requisitos funcionales, no funcionales y los flujos de negocio que gobernarán el desarrollo de DentiCore 2.0. Sirve como contrato técnico inmutable para la implementación de la arquitectura basada en microservicios simulados (Spring Boot), mensajería asíncrona y el frontend desacoplado (Angular) durante el ciclo de 7 Sprints.

## 2. Alcance y Exclusiones
Para garantizar la entrega en el plazo establecido, se establecen los siguientes límites:
* **In-Scope (En alcance):** Autenticación JWT/BCrypt, portal público de agendamiento web, Back-Office de gestión de historias clínicas, odontograma interactivo, motor transaccional de presupuestos y notificaciones asíncronas vía RabbitMQ.
* **Out-of-Scope (Fuera de alcance):** Integración con SDKs de pasarelas de pago reales (Stripe/Niubiz), facturación electrónica fiscal (SUNAT), orquestación en clústeres cloud (Kubernetes) y WebSockets en tiempo real. Los pagos se simularán estrictamente a nivel de base de datos.

## 3. Modelado de Procesos Críticos

### 3.1. Flujo de Captación y Agendamiento
*   **Escenario As-Is:** El paciente solicita información mediante un enlace que lo redirige a WhatsApp. El personal médico interrumpe sus labores clínicas para negociar horarios disponibles, generando tiempos de espera prolongados y posibles cruces de agenda.
*   **Escenario To-Be (DentiCore 2.0):** El paciente accede al Portal de Autogestión (Angular), visualiza los horarios disponibles en tiempo real y reserva su cita. El backend (Spring Boot) registra la cita en estado "Pendiente" y emite un evento asíncrono (RabbitMQ) para notificar la reserva sin bloquear el hilo de ejecución principal HTTP del usuario.

### 3.2. Flujo de Atención Clínica y Facturación
*   **Escenario As-Is:** El odontograma y el historial clínico se manejan físicamente o en aplicaciones locales aisladas. La facturación requiere un cálculo manual propenso a errores y discrepancias de caja.
*   **Escenario To-Be (DentiCore 2.0):** El especialista ingresa al Back-Office y actualiza el odontograma interactivo SVG. Cada tratamiento seleccionado se añade a un "Carrito de Servicios". Al confirmar, el motor transaccional consolida la cabecera y el detalle de la venta bajo transacciones ACID, asegurando la inmutabilidad financiera en PostgreSQL y reduciendo el tiempo de presupuestación a menos de 2 minutos.

## 4. Requisitos Funcionales (Historias de Usuario Épicas)
*   **F-01 (Identidad y Accesos):** El sistema debe permitir la autenticación de pacientes y odontólogos, validando credenciales cifradas con BCrypt y emitiendo un token JWT para el control seguro de sesiones y roles.
*   **F-02 (Gestión de Citas):** El sistema debe permitir la creación, confirmación y cancelación de citas, validando a nivel de base de datos la no superposición de horarios para un mismo especialista.
*   **F-03 (Historia Clínica):** El sistema debe permitir el registro de las atenciones clínicas asociadas a un paciente, vinculando automáticamente la cita previa operativa.
*   **F-04 (Odontograma Transaccional):** El sistema debe proveer una interfaz dinámica para registrar tratamientos por pieza dental, calculando automáticamente el subtotal, impuestos y total para su posterior procesamiento comercial.

## 5. Requisitos No Funcionales (NFRs)
*   **NFR-01 (Seguridad):** Las contraseñas en la base de datos deben estar hasheadas con algoritmo BCrypt. Los endpoints de la API deben estar protegidos mediante filtros de Spring Security interceptando los tokens JWT.
*   **NFR-02 (Rendimiento):** El tiempo de respuesta de las consultas de catálogo y disponibilidad de citas en el API Gateway no debe exceder los 500 milisegundos en el percentil 95.
*   **NFR-03 (Despliegue y Resiliencia):** La solución debe estar completamente contenerizada utilizando Docker, garantizando que los servicios de base de datos (PostgreSQL), mensajería (RabbitMQ) y backend puedan levantarse en conjunto mediante un único archivo `docker-compose.yml`. Se aplicará el patrón Circuit Breaker para evitar caídas en cascada.
*   **NFR-04 (Mantenibilidad):** El código backend debe adherirse a los principios SOLID, utilizando el patrón de inyección de dependencias de Spring, y separando estrictamente las responsabilidades en capas (Controllers, Services, Repositories, Entities).