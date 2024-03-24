const LISTAR_PACIENTES_URL = "http://localhost:8080/paciente/listar";

function llenarLista(idTabla = null, datos = null) {
  datos.forEach((paciente) => {
    crearFilaEnTabla(idTabla, paciente);
  });
}

function crearFilaEnTabla(idTabla = null, datos = null) {
  let tbody = document.getElementById(idTabla).getElementsByTagName("tbody")[0];

  let fila = document.createElement("tr");

  fila.innerHTML = `
  
      <td>${datos.id}</td>
      <td>${datos.nombre}</td>
      <td>${datos.apellido}</td>
      <td>${datos.dni}</td>
      <td>${datos.domicilio ? datos.domicilio.calle : ''}</td>
      <td>${datos.domicilio ? datos.domicilio.numero : ''}</td>
      <td>${datos.fechaIngreso}</td>

      <td><button>editar</button> <button onClick="eliminarPaciente(${datos.id})">eliminar</button></td>
    `;

  tbody.appendChild(fila);
}

//Odontologos

function fetchPacientes(apiUrl = null) {
  fetch(apiUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
    console.log(datos);
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
