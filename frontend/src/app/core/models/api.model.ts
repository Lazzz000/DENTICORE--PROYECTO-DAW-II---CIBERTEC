export interface LoginRequest {
  dni: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  rol: string;
}

export interface Especialidad {
  id: number;
  nombre: string;
}

export interface AgendarCitaRequest {
  idPaciente: number;
  idOdontologo: number;
  fechaHora: string;
  canalOrigen: string;
  montoAdelanto: number;
  referenciaAdelanto?: string;
}

export interface DetalleOdontograma {
  idElementoClinico: number;
  numeroPieza: number;
  diagnostico: string;
}

export interface GuardarOdontogramaRequest {
  idAtencionClinica: number;
  tipo: string;
  detalles: DetalleOdontograma[];
}

export interface ItemCarrito {
  idItemCatalogo: number;
  cantidad: number;
  precioAplicado: number;
}

export interface ProcesarVentaRequest {
  idPaciente: number;
  idCita: number;
  itemsCarrito: ItemCarrito[];
}

export interface UsuarioLigero {
  id: number;
  nombres: string;
  apellidos?: string;
  dni?: string;
}

export interface ItemCatalogo {
  id: number;
  nombre: string;
  precio?: number;
  activo?: boolean;
}

export interface RegistroPacienteRequest {
  dni: string;
  nombres: string;
  apellidos: string;
  correo: string;
  password: string;
  grupoSanguineo: string;
  fechaNacimiento: string;
}

export interface PacienteActivo extends UsuarioLigero {}

export interface OdontologoActivo extends UsuarioLigero {}

//Creacion de modelo para citas
export type EstadoCita =
  | 'PENDIENTE'
  | 'CONFIRMADA'
  | 'EN_SALA'
  | 'EN_CURSO'
  | 'ATENDIDA'
  | 'FINALIZADA'
  | 'CANCELADA';

export interface CitaListadoDTO {
  idCita: number;
  fechaHora: string;
  idPaciente: number;
  pacienteNombreCompleto: string;
  pacienteDni: string;
  idOdontologo: number;
  odontologoNombre: string;
  estado: EstadoCita;
  montoAdelanto: number;
}

export interface CitaResponseDTO {
  id: number;
  estado: EstadoCita;
  fechaHora: string;
  mensaje: string;
}

export interface ActualizarEstadoDTO {
  estado: EstadoCita;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}