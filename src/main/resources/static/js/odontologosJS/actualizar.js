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
  console.log(id);

  //const form = document.getElementById("formCrearOdontologo");
  //form.addEventListener("submit", submitForm);
}

document.addEventListener("DOMContentLoaded", onLoad);
