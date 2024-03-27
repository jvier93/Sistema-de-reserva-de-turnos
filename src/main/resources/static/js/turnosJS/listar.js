const LISTAR_TURNOS_URL = "http://localhost:8080/turno/listar";
const ELIMINAR_TURNO_URL = "http://localhost:8080/turno/eliminar";

function llenarLista(idTabla = null, datos = null) {
  datos.forEach((turno) => {
    crearFilaEnTabla(idTabla, turno);
  });
}

function crearFilaEnTabla(idTabla = null, turno = null) {
  let tbody = document.getElementById(idTabla).getElementsByTagName("tbody")[0];

  let fila = document.createElement("tr");

  fila.innerHTML = `
      <td>${turno.id}</td>
      <td>${turno.odontologo.nombre}</td>
      <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
      <td>${turno.fecha}</td>
      <td>${turno.hora}</td>
      <td><a href="actualizar.html?id=${turno.id}">Actualizar</a></td>
      <td><button onClick="eliminarTurno(${turno.id})">Eliminar</button></td>
    `;

  tbody.appendChild(fila);
}

function fetchTurnos(apiUrl = null) {
  fetch(apiUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }
      return respuesta.json();
    })
    .then((datos) => {
      console.log(datos);
      llenarLista("tablaTurnos", datos);
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function onLoad() {
  fetchTurnos(LISTAR_TURNOS_URL);
}

document.addEventListener("DOMContentLoaded", onLoad);

function eliminarTurno(idTurno) {
  fetch(`${ELIMINAR_TURNO_URL}/${idTurno}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }
      fetchTurnos(LISTAR_TURNOS_URL);
      Swal.fire({
        title: "Eliminar",
        text: "Turno eliminado exitosamente",
        icon: "success",
      });
    })
    .catch((error) => {
      console.error(error);
      Swal.fire({
        title: "Eliminar",
        text: "Error al eliminar el turno. Consulta la consola para más detalles.",
        icon: "error",
      });
    });
}
