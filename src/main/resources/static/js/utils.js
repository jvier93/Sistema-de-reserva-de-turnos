function llenarLista(idTabla = null, datos = null) {
  datos.forEach((odontologo) => {
    crearFilaEnTabla(idTabla, odontologo);
  });
}

function crearFilaEnTabla(idTabla = null, datos = null) {
  let tbody = document.getElementById(idTabla).getElementsByTagName("tbody")[0];

  let fila = document.createElement("tr");

  fila.innerHTML = `

    <td>${datos.id}</td>
    <td>${datos.nombre}</td>
    <td>${datos.apellido}</td>
    <td>${datos.matricula}</td>
    <td><button>editar</button> <button>eliminar</button></td>
  `;

  tbody.appendChild(fila);
}

//Odontologos

function fetchOdontologos(apiUrl = null) {
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

function onLoad() {
  fetchOdontologos("http://localhost:8080/odontologo/listar");
}

// Asigna la función al evento DOMContentLoaded
document.addEventListener("DOMContentLoaded", onLoad);
