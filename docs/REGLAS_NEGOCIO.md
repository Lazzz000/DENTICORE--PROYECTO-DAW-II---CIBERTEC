# Reglas de Negocio y Arquitectura - DentiCore 2.0

Este documento define la lógica transaccional y operativa del sistema. Las implementaciones en código (Frontend y Backend) deben regirse estrictamente por estas directrices (Fase 1).

## 1. Máquina de Estados de Citas Clínicas (Flujo Logístico)
El ciclo de vida de una cita médica está estrictamente tipado. Los estados no pueden saltarse y definen responsabilidades separadas para Recepción y Odontólogos.
* **PENDIENTE:** Cita reservada sin pago/adelanto confirmado.
* **CONFIRMADA:** Cita con adelanto validado. Lista para el día de atención.
* **EN_SALA_DE_ESPERA:** El paciente llegó a la clínica (Trigger: Recepción).
* **ENVIADO_A_CONSULTORIO:** Recepción notifica al doctor que el paciente sube (Trigger: Recepción).
* **EN_CURSO:** El paciente está en el sillón. Inicia el contador de tiempo médico (Trigger: Odontólogo).
* **ATENDIDA:** El doctor finaliza y guarda el Odontograma (Trigger: Odontólogo).
* **CANCELADA:** Cita anulada (Trigger: Paciente/Administrador).

## 2. Motor de Agendamiento y Adelantos (Transacción de 2 Pasos)
* **Paso 1 (Reserva):** El sistema crea la cita en estado `PENDIENTE`. Las citas desde la web se asumen bajo el ítem "Consulta de Diagnóstico" para evitar diagnósticos erróneos por parte del paciente.
* **Paso 2 (Pago):** El sistema procesa el pago asíncronamente. Si el pago es en "Efectivo", no se requiere código de operación. Tras el pago, la cita pasa a `CONFIRMADA` y el `montoAdelanto` se registra para la liquidación final.
* **Validación de Conflictos:** No se permite crear una cita si el Odontólogo ya tiene otra en estado `PENDIENTE`, `CONFIRMADA`, `EN_SALA_DE_ESPERA`, `ENVIADO_A_CONSULTORIO` o `EN_CURSO` en la misma fecha y hora exacta.

## 3. Odontograma Dinámico (Procedimientos)
El registro dental no se cobra por "Tratamiento macro", sino por unidad procedimental (por diente).
* **PROSPECTADO:** Diagnóstico o tratamiento futuro. Se guarda pero no se cobra.
* **REALIZADO:** Procedimiento ejecutado en la sesión actual. Al guardar, solo estos ítems se envían al motor de facturación. Los ítems `PROSPECTADO` reaparecerán en la siguiente cita.

## 4. Venta Directa y Cliente Anónimo (POS)
* **Regla SUNAT:** Los productos físicos (ej. cepillos) aplican 18% IGV. Los servicios médicos 0% IGV.
* **Cliente Genérico:** Para ventas rápidas presenciales menores al límite tributario que no requieran DNI, el sistema facturará utilizando el registro maestro `CLIENTE VARIOS` (DNI: `00000000`), manteniendo la integridad referencial de la base de datos sin generar historias clínicas basura.