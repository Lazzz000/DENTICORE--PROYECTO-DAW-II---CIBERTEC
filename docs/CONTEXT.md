# Documento Estratégico: Contexto y Objetivos (CONTEXT.md)

**Proyecto:** DENTICORE-PROYECTO DAWII-CIBERTEC
**Cliente:** Clínica Dental "Dr. Dave Cáceres - Odontología Especializada"

## 1. Análisis del Entorno (SEPTE)

El desarrollo e implementación de DentiCore 2.0 se fundamenta en las siguientes variables del entorno que exigen la modernización de la clínica:

* **Social (Tendencia del Paciente):** El consumidor de salud moderno exige "fricción cero". El 70% de los pacientes inician su búsqueda de atención médica en línea, y el 80% afirma que la disponibilidad de agendamiento web influye en su elección de proveedor. El proceso manual y asíncrono vía WhatsApp representa un riesgo crítico de pérdida de pacientes. DentiCore 2.0 soluciona esto delegando la captura de pacientes a un portal de autogestión en tiempo real[cite: 9].
* **Económico (Optimización de Recursos):** La ineficiencia operativa genera costos ocultos. El personal médico clínico invierte horas facturables en tareas administrativas manuales (agendamiento y cobranza)[cite: 9]. La automatización mediante software libera este tiempo, proyectando una reducción del 80% en estas labores e impactando directamente en la rentabilidad diaria[cite: 9].
* **Político/Legal (Protección de Datos):** La manipulación física de historias clínicas y el envío de datos médicos por canales no encriptados expone a la clínica a vulneraciones legales. El sistema implementará autenticación segura gestionada con tokens JWT y cifrado de contraseñas mediante `BCryptPasswordEncoder` para cumplir estrictamente con normativas de protección de datos como la Ley N° 29733[cite: 9].
* **Tecnológico (Escalabilidad y Continuidad):** La dependencia de hardware local y monolitos aísla los datos y limita el crecimiento[cite: 9]. La migración hacia un ecosistema distribuido, donde el backend se orquestará en microservicios utilizando Spring Boot y un frontend desacoplado en Angular, garantiza escalabilidad[cite: 9]. Se integrará RabbitMQ para mensajería asíncrona y Docker para garantizar alta disponibilidad y respuestas del servidor en milisegundos[cite: 9].
* **Ecológico (Paperless):** La digitalización absoluta de la historia clínica (odontograma interactivo) y la facturación elimina el uso de recursos impresos, reduciendo los costos de material de oficina y la huella de carbono de las operaciones diarias[cite: 9].

## 2. Objetivos SMART

Los objetivos del proyecto definen el éxito técnico y de valor comercial, enmarcados en el ciclo de desarrollo de 7 Sprints:

* **OBJ 1 (Eficiencia Operativa / Transaccional):** Reducir en un 80% el tiempo invertido por el personal clínico en labores de cotización y facturación, logrando generar presupuestos en menos de 2 minutos mediante la implementación de un Odontograma interactivo que procese transacciones ACID en PostgreSQL para la Semana 5.
* **OBJ 2 (Captación / Comercial):** Incrementar la autonomía del paciente y la tasa de retención desplegando un portal web (Angular SPA) que permita el agendamiento y validación de citas concurrentes en tiempo real, eliminando la dependencia de mensajería manual para la Semana 4.
* **OBJ 3 (Continuidad de Negocio / Técnico):** Garantizar la operatividad ininterrumpida de la clínica y la protección de datos sensibles mediante el despliegue de una arquitectura aislada (Docker), tolerancia a fallos en comunicaciones (RabbitMQ) y seguridad de accesos (JWT/BCrypt), asegurando un producto listo para producción simulada al cierre de la Semana 7.