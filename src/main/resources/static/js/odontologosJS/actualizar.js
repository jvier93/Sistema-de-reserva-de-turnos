function onLoad() {
  const url = new URL(window.location.href);

  const parametros = new URLSearchParams(url.search);

  if (!parametros.has("id")) {
    Swal.fire({
      title: "Actualizar",
      text: "Falta el id del Odontólogo a actualizar",
      icon: "warning",
    });
    return;
  }

  const id = parametros.get("id");
  fetchOdontologo(OBTENER_ODONTOLOGO_URL, id);

  const form = document.getElementById("formActualizarOdontologo");
  form.addEventListener("submit", submitForm);
}

function fetchOdontologo(apiUrl = null, idOdontologo = null) {
  if (apiUrl === null || idOdontologo === null) {
    return;
  }

  const fetchUrl = `${apiUrl}/${idOdontologo}`;

  fetch(fetchUrl)
    .then((respuesta) => {
      if (!respuesta.ok) {
        throw new Error("Network response was not ok");
      }

      return respuesta.json();
    })
    .then((datos) => {
      cargarOdontologoEnFormulario("formActualizarOdontologo", datos);
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
  form.querySelector('[name="nombre"]').value = datos.nombre;
  form.querySelector('[name="apellido"]').value = datos.apellido;
  form.querySelector('[name="matricula"]').value = datos.matricula;
  form.querySelector("#submit").setAttribute("data-odontologo-id", datos.id);
}

function submitForm(e) {
  e.preventDefault();

  const form = e.target;
  const formData = new FormData(form);

  const nombre = formData.get("nombre");
  const apellido = formData.get("apellido");
  const matricula = formData.get("matricula");
  const id = form.querySelector("#submit").getAttribute("data-odontologo-id");

  actualizarOdontologo(
    ACTUALIZAR_ODONTOLOGO_URL,
    id,
    nombre,
    apellido,
    matricula
  );
}

function actualizarOdontologo(apiUrl = null, id, nombre, apellido, matricula) {
  const odontologo = {
    id: id,
    nombre: nombre,
    apellido: apellido,
    matricula: matricula,
  };

  fetch(apiUrl, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(odontologo),
  })
    .then((respuesta) => {
      console.log(respuesta);
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }

      Swal.fire({
        title: "Actualizar",
        text: "Odontólogo actualizado exitosamente",
        icon: "success",
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
