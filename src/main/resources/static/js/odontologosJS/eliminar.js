const ELIMINAR_ODONTOLOGOS_URL = "http://localhost:8080/odontologo/eliminar"; //Seguido del id a eliminar /id

// function eliminarOdontologo(idOdontologo) {
//   console.log(idOdontologo);
//   fetch(`${ELIMINAR_ODONTOLOGOS_URL}/${idOdontologo}`, {
//     method: "DELETE",
//     headers: {
//       "Content-Type": "application/json",
//     },
//   })
//     .then((respuesta) => {
//       console.log(respuesta);
//       console.log("La respuesta es ok" + respuesta.ok);
//       if (!respuesta.ok) {
//         throw new Error(`Error response status ${respuesta.status}`);
//       }
//       return respuesta.json();
//     })
//     .then((datos) => {
//       Swal.fire({
//         title: "Eliminar",
//         text: "Odontólogo eliminado exitosamente",
//         icon: "success",
//       });
//     })
//     .catch((error) => {
//       console.error(error);
//       Swal.fire({
//         title: "Eliminar",
//         text: "Error al eliminar el odontólogo. Consulta la consola para más detalles.",
//         icon: "error",
//       });
//     });
// }
function eliminarOdontologo(idOdontologo) {
  console.log(idOdontologo);
  fetch(`${ELIMINAR_ODONTOLOGOS_URL}/${idOdontologo}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((respuesta) => {
      console.log(respuesta);
      console.log("La respuesta es ok" + respuesta.ok);
      if (!respuesta.ok) {
        throw new Error(`Error response status ${respuesta.status}`);
      }
      // No intentamos analizar la respuesta como JSON, ya que no esperamos datos JSON.
      // Simplemente mostramos el mensaje de éxito.
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
