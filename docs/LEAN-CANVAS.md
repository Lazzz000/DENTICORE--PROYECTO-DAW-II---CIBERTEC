# Lean Canvas: DentiCore 2.0

**Proyecto:** Plataforma Web Transaccional y de Gestión Clínica "DentiCore 2.0"  
**Cliente:** Clínica Dental "Dr. Dave Cáceres - Odontología Especializada"  
**Ubicación:** San Juan de Lurigancho, Lima, Perú  
**Naturaleza arquitectónica:** Ecosistema nativo de la nube (Spring Boot, Angular, Docker)  

---

## 1. Problema (Problem)
* **Sobrecarga operativa:** La clínica carece de personal administrativo exclusivo, obligando a los especialistas a asumir la carga operativa total, lo que reduce el tiempo de atención clínica efectiva.
* **Fricción en la captación:** El proceso de reserva depende de una negociación manual, asíncrona y no estructurada por WhatsApp, generando demoras de horas y un alto margen de error en cruces de horarios.
* **Aislamiento de la información:** La gestión de historias clínicas y odontogramas se realiza en formatos físicos o sistemas locales aislados, dificultando el acceso ubicuo y la trazabilidad de los datos.
* **Riesgo transaccional:** No existe un motor que vincule directamente el diagnóstico con el cobro, ocasionando discrepancias de caja entre los procedimientos ejecutados y lo facturado.

## 2. Segmentos de Clientes (Customer Segments)
* **Pacientes (B2C):** Consumidores digitales que exigen "fricción cero", disponibilidad 24/7 y plataformas de autogestión para iniciar su viaje de atención médica de forma autónoma.
* **Usuarios Internos (Especialistas Clínicos y Gerencia):** Odontólogos que requieren un sistema resiliente para delegar tareas de agendamiento y cobro, permitiéndoles concentrarse exclusivamente en la labor médica y garantizando el control de caja.

## 3. Propuesta de Valor Única (Unique Value Proposition)
Plataforma web integral de alta disponibilidad que automatiza el agendamiento y el ciclo de ingresos de la clínica dental. Transforma la operación hacia un modelo *Paperless* que asegura la integridad de los historiales clínicos mediante transacciones ACID. Recupera el 80% del tiempo invertido en labores administrativas, convirtiéndolo en capacidad instalada directamente facturable.

## 4. Solución (Solution)
* **Front-Office (Portal SPA en Angular):** Portal público de autogestión donde el paciente visualiza el catálogo de especialidades y reserva citas validando la disponibilidad en tiempo real contra la base de datos.
* **Back-Office (Core Clínico en Spring Boot):** Panel de administración y gestión clínica que centraliza historias y diagnósticos de manera segura (JWT y encriptación BCrypt).
* **Motor Transaccional Interactivo:** Odontograma dinámico en SVG acoplado a un "carrito de servicios" que automatiza la generación de presupuestos y cierres de venta en una única transacción de base de datos.

## 5. Canales (Channels)
* **Portal Web interactivo:** Accesible 24/7 como canal digital principal de adquisición de pacientes.
* **Notificaciones Asíncronas:** Envío de confirmaciones de eventos clínicos operadas por un Message Broker (RabbitMQ) para garantizar inmediatez sin bloquear la experiencia del usuario.

## 6. Flujo de Ingresos (Revenue Streams)
* **Optimización de Capacidad Instalada:** Incremento directo de la rentabilidad diaria generado por la liberación de horas médicas, permitiendo la atención de un mayor volumen de pacientes.
* **Preparación Omnicanal:** Arquitectura lista para la futura recaudación de pagos mediante integración de pasarelas de cobro online (actualmente simulado en el motor de ventas interno para esta fase del proyecto).

## 7. Estructura de Costos (Cost Structure)
* **Infraestructura y Orquestación:** Costos asociados al alojamiento (servidores/nube) o mantenimiento del entorno contenedorizado (Docker/PostgreSQL).
* **Mantenimiento y Soporte:** Inversión en actualizaciones de la arquitectura orientada a microservicios simulados (Shared Database).
* **Operaciones de Terceros (Visión a futuro):** Comisiones de pasarelas de pago y operadores de servicios electrónicos (OSE) para facturación fiscal.

## 8. Métricas Clave (Key Metrics)
* Porcentaje de reducción de horas invertidas en tareas administrativas por especialista.
* Tasa de conversión de agendamientos web autónomos versus mensajería manual (WhatsApp).
* Uptime (tiempo de disponibilidad) del sistema y latencia en el procesamiento de transacciones comerciales.

## 9. Ventaja Injusta (Unfair Advantage)
Centralización nativa del flujo clínico y comercial en una única herramienta construida bajo el patrón *Backend for Frontend (BFF)*. A diferencia de soluciones de mercado genéricas, DentiCore adapta su infraestructura asíncrona a la operatividad exacta de la clínica, eliminando la dependencia de licencias de software de terceros y formatos físicos descentralizados.