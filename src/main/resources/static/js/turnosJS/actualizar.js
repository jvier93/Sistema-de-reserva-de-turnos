function onLoad() {
  const url = new URL(window.location.href);

  const parametros = new URLSearchParams(url.search);

  if (!parametros.has("id")) {
    Swal.fire({
      title: "Actualizar",
      text: "Falta el id del Turno a actualizar",
      icon: "warning",
    });
    return;
  }

  const id = parametros.get("id");

  //Obtenemos los odontologos y los cargamos en su select
  fetch("/odontologo/listar")
    .then((response) => response.json())
    .then((data) => {
      const select = document.getElementById("odontologoSelect");
      data.forEach((odontologo) => {
        const option = document.createElement("option");
        option.value = odontologo.id;
        option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
        select.appendChild(option);
      });
    })
    .catch((error) => console.error("Error al obtener odontólogos:", error));

  //Obtenemos los pacientes y los cargamos en su select
  fetch("/paciente/listar")
    .then((response) => response.json())
    .then((data) => {
      const select = document.getElementById("pacienteSelect");
      data.forEach((paciente) => {
        const option = document.createElement("option");
        option.value = paciente.id;
        option.textContent = `${paciente.nombre} ${paciente.apellido}`;
        select.appendChild(option);
      });
    })
    .catch((error) => console.error("Error al obtener pacientes:", error));

  fetchTurno(OBTENER_TURNO_URL, id);

  const form = document.getElementById("formActualizarTurno");
  form.addEventListener("submit", submitForm);
}

function fetchTurno(apiUrl = null, idTurno = null) {
  if (apiUrl === null || idTurno === null) {
    return;
  }

  const fetchUrl = `${apiUrl}/${idTurno}`;

  fetch(fetchUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
      cargarOdontologoEnFormulario("formActualizarTurno", datos);
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function cargarOdontologoEnFormulario(idFormulario = null, datos = null) {
  if (idFormulario === null || datos === null) {
    return;
  }
  const form = document.getElementById(idFormulario);
  form.querySelector('[name="idOdontologo"]').value = datos.odontologo.id;
  form.querySelector('[name="idPaciente"]').value = datos.paciente.id;
  form.querySelector('[name="fecha"]').value = datos.fecha;
  form.querySelector('[name="hora"]').value = datos.hora;
  form.querySelector("#submit").setAttribute("data-turno-id", datos.id);
}

function submitForm(e) {
  e.preventDefault();

  const form = e.target;
  const formData = new FormData(form);

  const idOdontologo = formData.get("idOdontologo");
  const idPaciente = formData.get("idPaciente");
  const fecha = formData.get("fecha");
  const hora = formData.get("hora");
  const idTurno = form.querySelector("#submit").getAttribute("data-turno-id");

  actualizarTurno(
    ACTUALIZAR_TURNO_URL,
    idTurno,
    idOdontologo,
    idPaciente,
    fecha,
    hora
  );
}

function actualizarTurno(
  apiUrl = null,
  idTurno,
  idOdontologo,
  idPaciente,
  fecha,
  hora
) {
  const turnoData = {
    id: idTurno,
    odontologo: {
      id: idOdontologo,
    },
    paciente: {
      id: idPaciente,
    },
    fecha,
    hora,
  };

  fetch(apiUrl, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(turnoData),
  })
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }

      Swal.fire({
        title: "Actualizar",
        text: "Turno actualizado exitosamente",
        icon: "success",
      }).then(() => {
        window.location.href = "http://localhost:8080/turnosHTML/listar.html";
      });
    })

    .catch((error) => {
      console.error(error);
      Swal.fire({
        title: "Actualizar",
        text: "Error al actualizar Checkear consola ",
        icon: "error",
      });
    });
}

document.addEventListener("DOMContentLoaded", onLoad);
