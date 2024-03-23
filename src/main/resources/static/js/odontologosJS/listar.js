function llenarLista(idTabla = null, datos = null) {
  let tbody = document.getElementById(idTabla).getElementsByTagName("tbody")[0];

  if (datos.length === 0) {
    tbody.innerHTML = `<p>No hay datos</p>`;
    return;
  }
  tbody.innerHTML = ``;
  datos.forEach((odontologo) => {
    crearFilaEnTabla(tbody, odontologo);
  });
}

function crearFilaEnTabla(tBody, datos = null) {
  let fila = document.createElement("tr");

  fila.innerHTML = `
  
      <td>${datos.id}</td>
      <td>${datos.nombre}</td>
      <td>${datos.apellido}</td>
      <td>${datos.matricula}</td>
      <td><a class="btn" href="actualizar.html?id=${datos.id}">editar</a> <button onClick="eliminarOdontologo(${datos.id})">eliminar</button></td>
    `;

  tBody.appendChild(fila);
}

//Odontologos

function fetchOdontologos(apiUrl = null) {
  console.log("Se ejecuto fetch");
  fetch(apiUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
      llenarLista("tablaOdontologos", datos);
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function eliminarOdontologo(idOdontologo) {
  console.log(idOdontologo);
  fetch(`${ELIMINAR_ODONTOLOGOS_URL}/${idOdontologo}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }

      fetchOdontologos(LISTAR_ODONTOLOGOS_URL);

      Swal.fire({
        title: "Eliminar",
        text: "Odontólogo eliminado exitosamente",
        icon: "success",
      });
    })
    .catch((error) => {
      console.error(error);
      Swal.fire({
        title: "Eliminar",
        text: "Error al eliminar el odontólogo. Consulta la consola para más detalles.",
        icon: "error",
      });
    });
}

function onLoad() {
  fetchOdontologos(LISTAR_ODONTOLOGOS_URL);
}

// Asigna la función al evento DOMContentLoaded
document.addEventListener("DOMContentLoaded", onLoad);
