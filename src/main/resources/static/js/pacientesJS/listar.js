function llenarLista(idTabla = null, datos = null) {
  let tbody = document.getElementById(idTabla).getElementsByTagName("tbody")[0];

  if (datos.length === 0) {
    tbody.innerHTML = `<p>No hay datos</p>`;
    return;
  }
  tbody.innerHTML = ``;
  datos.forEach((paciente) => {
    crearFilaEnTabla(tbody, paciente);
  });
}

function crearFilaEnTabla(tbody = null, datos = null) {
  let fila = document.createElement("tr");

  fila.innerHTML = `
  
      <td>${datos.id}</td>
      <td>${datos.nombre}</td>
      <td>${datos.apellido}</td>
      <td>${datos.dni}</td>
      <td>${datos.domicilio ? datos.domicilio.calle : ""}</td>
      <td>${datos.domicilio ? datos.domicilio.numero : ""}</td>
      <td>${datos.fechaIngreso}</td>

      <td><a class="btn btn-secondary btn-sm" href="actualizar.html?id=${
        datos.id
      }">Actualizar</a></td>
      <td><button class="btn btn-danger btn-sm" onClick="eliminarPaciente(${
        datos.id
      })">eliminar</button></td>
    `;

  tbody.appendChild(fila);
}

function fetchPacientes(apiUrl = null) {
  fetch(apiUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
      llenarLista("tablaPacientes", datos);
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function onLoad() {
  fetchPacientes(LISTAR_PACIENTES_URL);
}

document.addEventListener("DOMContentLoaded", onLoad);

// eliminar paciente

function eliminarPaciente(idPaciente) {
  fetch(`${ELIMINAR_PACIENTE_URL}/${idPaciente}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }

      fetchPacientes(LISTAR_PACIENTES_URL);

      Swal.fire({
        title: "Eliminar",
        text: "Paciente eliminado exitosamente",
        icon: "success",
      });
    })
    .catch((error) => {
      console.error(error);
      Swal.fire({
        title: "Eliminar",
        text: "Error al eliminar el paciente. Consulta la consola para más detalles.",
        icon: "error",
      });
    });
}

function onLoad() {
  fetchPacientes(LISTAR_PACIENTES_URL);
}

// Asigna la función al evento DOMContentLoaded
document.addEventListener("DOMContentLoaded", onLoad);
