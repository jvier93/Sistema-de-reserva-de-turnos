const DOMINIO = "localhost:8080";
const LISTAR_ODONTOLOGOS_URL = `http://${DOMINIO}/odontologo/listar`;
const ELIMINAR_ODONTOLOGOS_URL = `http://${DOMINIO}/odontologo/eliminar`;
const GUARDAR_ODONTOLOGO_URL = `http://${DOMINIO}/odontologo/guardar`; //Seguido del /id del odontologo a eliminar
const ACTUALIZAR_ODONTOLOGO_URL = `http://${DOMINIO}/odontologo/actualizar`;
const OBTENER_ODONTOLOGO_URL = `http://${DOMINIO}/odontologo`; //Seguido del /id del odontologo
const LISTAR_PACIENTES_URL = "http://localhost:8080/paciente/listar";
const GUARDAR_PACIENTE_URL = "http://localhost:8080/paciente/guardar";
const ELIMINAR_PACIENTE_URL = "http://localhost:8080/paciente/eliminar";
const LISTAR_TURNOS_URL = `http://${DOMINIO}/turno/listar`;
const ELIMINAR_TURNOS_URL = `http://${DOMINIO}/turno/eliminar`; //Seguido del /id del turno a eliminar
const OBTENER_TURNO_URL = `http://${DOMINIO}/turno`; //Seguido del /id del turno
const ACTUALIZAR_TURNO_URL = `http://${DOMINIO}/turno/actualizar`;
