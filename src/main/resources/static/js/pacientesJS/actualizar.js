const OBTENER_PACIENTE_URL = "http://localhost:8080/paciente";
const ACTUALIZAR_PACIENTE_URL = "http://localhost:8080/paciente/actualizar"

function onLoad() {
  const url = new URL(window.location.href);

  const parametros = new URLSearchParams(url.search);

  if (!parametros.has("id")) {
    Swal.fire({
      title: "Actualizar",
      text: "Falta el id del Paciente a actualizar",
      icon: "warning",
    });
    return;
  }

  const id = parametros.get("id");
  fetchPaciente(OBTENER_PACIENTE_URL, id);

  const form = document.getElementById("formActualizarPaciente");
  form.addEventListener("submit", submitForm);
}

function fetchPaciente(apiUrl = null, idPaciente = null) {
  if (apiUrl === null || idPaciente === null) {
    return;
  }

  const fetchUrl = `${apiUrl}/${idPaciente}`;

  fetch(fetchUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
      cargarPacienteEnFormulario("formActualizarPaciente", datos);
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function cargarPacienteEnFormulario(idFormulario = null, datos = null) {
  if (idFormulario === null || datos === null) {
    return;
  }
  const form = document.getElementById(idFormulario);
  form.querySelector('[name="nombre"]').value = datos.nombre;
  form.querySelector('[name="apellido"]').value = datos.apellido;
  form.querySelector('[name="dni"]').value = datos.dni;
  form.querySelector('[name="calle"]').value = datos.domicilio.calle;
  form.querySelector('[name="numero"]').value = datos.domicilio.numero;
  form.querySelector('[name="localidad"]').value = datos.domicilio.localidad;
  form.querySelector('[name="provincia"]').value = datos.domicilio.provincia;
  form.querySelector('[name="fechaIngreso"]').value = datos.fechaIngreso;
  form.querySelector("#submit").setAttribute("data-paciente-id", datos.id);
}

function submitForm(e) {
  e.preventDefault();

  const form = e.target;
  const formData = new FormData(form);

  const nombre = formData.get("nombre");
  const apellido = formData.get("apellido");
  const dni = formData.get("dni");
  const calle = formData.get("calle");
  const numero = formData.get("numero");
  const localidad = formData.get("localidad");
  const provincia = formData.get("provincia");
  const fechaIngreso = formData.get("fechaIngreso");

  const id = form.querySelector("#submit").getAttribute("data-paciente-id");

  actualizarPaciente(
    ACTUALIZAR_PACIENTE_URL,
    id,
    nombre,
    apellido,
    dni,
    calle,
    numero,
    localidad,
    provincia,
    fechaIngreso
  );
}

function actualizarPaciente(apiUrl = null, id, nombre, apellido, dni, calle, numero, localidad, provincia, fechaIngreso) {
  const paciente = {
    id: id,
    nombre: nombre,
    apellido: apellido,
    dni: dni,
    domicilio: {
       calle: calle,
       numero: numero,
       localidad: localidad,
       provincia: provincia,
    },
     fechaIngreso: fechaIngreso,

  };

  fetch(apiUrl, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(paciente),
  })
    .then((respuesta) => {
      console.log(respuesta);
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }

      Swal.fire({
        title: "Actualizar",
        text: "Paciente actualizado exitosamente",
        icon: "success",
      }).then(() => {
        window.location.href = "http://localhost:8080/pacientesHTML/listar.html";
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