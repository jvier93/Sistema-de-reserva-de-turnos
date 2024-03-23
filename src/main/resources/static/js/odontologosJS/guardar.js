function submitForm(e) {
  e.preventDefault();

  const form = e.target;
  const formData = new FormData(form);

  const nombre = formData.get("nombre");
  const apellido = formData.get("apellido");
  const matricula = formData.get("matricula");

  guardarOdontologo(GUARDAR_ODONTOLOGO_URL, nombre, apellido, matricula);
  e.target.reset();
}

function guardarOdontologo(apiUrl = null, nombre, apellido, matricula) {
  const nuevoOdontologo = {
    nombre: nombre,
    apellido: apellido,
    matricula: matricula,
  };

  fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(nuevoOdontologo),
  })
    .then((respuesta) => {
      console.log(respuesta);
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }
      return respuesta.json();
    })
    .then((datos) => {
      Swal.fire({
        title: "Guardar",
        text: "Odontólogo guardado exitosamente",
        icon: "success",
      });
    })
    .catch((error) => {
      console.error(error);
      Swal.fire({
        title: "Guardar",
        text: "Error al guardar Checkear consola ",
        icon: "error",
      });
    });
}

function onLoad() {

  const form = document.getElementById("formCrearOdontologo");
  form.addEventListener("submit", submitForm);
}

document.addEventListener("DOMContentLoaded", onLoad);
