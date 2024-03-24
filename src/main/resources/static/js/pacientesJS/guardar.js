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
  const calle = formData.get("calle");
  const numero = formData.get("numero");
  const localidad = formData.get("localidad");
  const provincia = formData.get("provincia");
  const fechaIngreso = formData.get("fechaIngreso");
  console.log("se submitteo");


  guardarPaciente(GUARDAR_PACIENTE_URL, nombre, apellido, dni, calle, numero, localidad, provincia, fechaIngreso);
}

function guardarPaciente(apiUrl = null, nombre, apellido, dni, calle, numero, localidad, provincia, fechaIngreso) {
  const nuevoPaciente = {
      nombre: nombre,
      apellido: apellido,
      dni: dni,
      domicilio: {
        calle: calle,
        numero: numero,
        localidad: localidad,
        provincia: provincia
      },
      fechaIngreso: fechaIngreso
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
