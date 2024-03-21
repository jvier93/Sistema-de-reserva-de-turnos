const GUARDAR_PACIENTE_URL = "http://localhost:8080/paciente/guardar";

function onLoad() {
  const form = document.getElementById("formCrearPaciente");
  form.addEventListener("submit", submitForm);
}

function submitForm(e) {
  e.preventDefault();

  const form = e.target;
  const formData = new FormData(form);

  const nombre = formData.get("nombre");
  const apellido = formData.get("apellido");
  const dni = formData.get("dni");
  //const domicilio = formData.get("domicilio")
  //const fechaIngreso = formData.get("fechaIngreso")
  console.log("se submitteo")


  guardarPaciente(GUARDAR_PACIENTE_URL, nombre, apellido, dni);
}

function guardarPaciente(apiUrl = null, nombre, apellido, dni) {
  const nuevoPaciente = {
    nombre: nombre,
    apellido: apellido,
    dni: dni
  };

  fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(nuevoPaciente),
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
        text: "Paciente guardado exitosamente",
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

document.addEventListener("DOMContentLoaded", onLoad);
