function llenarLista(idTabla = null, datos = null) {
  let tbody = document.getElementById(idTabla).getElementsByTagName("tbody")[0];

  if (datos.length === 0) {
    tbody.innerHTML = `<p>No hay datos</p>`;
    return;
  }
  tbody.innerHTML = ``;
  datos.forEach((turno) => {
    crearFilaEnTabla(tbody, turno);
  });
}

function crearFilaEnTabla(tBody, datos = null) {
  let fila = document.createElement("tr");
  fila.innerHTML = `
    
        <td>${datos.id}</td>
        <td>${datos.odontologo.nombre} ${datos.odontologo.apellido}</td>
        <td>${datos.paciente.nombre} ${datos.paciente.apellido}</td>
        <td>${datos.fecha}</td>
        <td>${datos.hora}</td>
        <td><a class="btn btn-secondary btn-sm" href="actualizar.html?id=${datos.id}">editar</a> <button class="btn btn-danger btn-sm" onClick="eliminarTurno(${datos.id})">eliminar</button></td>
      `;

  tBody.appendChild(fila);
}

function fetchTurnos(apiUrl = null) {
  if (apiUrl === null) {
    return;
  }

  fetch(apiUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
      llenarLista("tablaTurnos", datos);
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function eliminarTurno(idTurno) {
  fetch(`${ELIMINAR_TURNOS_URL}/${idTurno}`, {
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

function onLoad() {
  fetchTurnos(LISTAR_TURNOS_URL);
}

// Asigna la función al evento DOMContentLoaded
document.addEventListener("DOMContentLoaded", onLoad);
