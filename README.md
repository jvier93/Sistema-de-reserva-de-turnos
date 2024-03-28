# Sistema de Reserva de Turnos para Clínica Odontológica  
## Proyecto Integrador materia backend

## Introducción

Se propone el desarrollo de un sistema para administrar la reserva de turnos en una clínica odontológica, abordando desde la gestión de odontólogos y pacientes hasta la asignación de turnos.

## Requerimientos Funcionales

### Administración de Odontólogos

- **Listar Odontólogos:** Visualizar todos los odontólogos registrados.
- **Agregar Odontólogos:** Incorporar nuevos odontólogos al sistema.
- **Modificar Odontólogos:** Actualizar datos de odontólogos existentes.
- **Eliminar Odontólogos:** Remover odontólogos del sistema.
- **Datos de Odontólogos:** Registrar apellido, nombre y matrícula.

### Administración de Pacientes

- **Listar Pacientes:** Mostrar todos los pacientes inscritos.
- **Agregar Pacientes:** Añadir nuevos pacientes al sistema.
- **Modificar Pacientes:** Editar información de pacientes existentes.
- **Eliminar Pacientes:** Eliminar pacientes del registro.
- **Datos de Pacientes:** Almacenar nombre, apellido, domicilio, DNI y fecha de alta.

### Registro de Turnos

- **Asignar Turno:** Permitir la reserva de un turno a un paciente con un odontólogo específico en una fecha y hora determinadas.

## Requerimientos Técnicos

### Capa de Entidades de Negocio

- Implementación de clases Java siguiendo el paradigma orientado a objetos para modelar el negocio.

### Capa de Acceso a Datos (Repository)

- Clases encargadas de interactuar con la base de datos.

### Capa de Datos (Base de Datos)

- Uso de la base de datos H2, modelada a través de un esquema entidad-relación.

### Capa de Negocio (Service)

- Clases service para desacoplar el acceso a datos de la capa de presentación.

### Capa de Presentación

- Desarrollo de interfaces web utilizando Spring Boot MVC, con opciones de HTML+JavaScript.

### Manejo de Excepciones

- Registro de cualquier excepción que se genere dentro del sistema.

### Pruebas Unitarias

- Realización de tests unitarios para asegurar la calidad y funcionamiento del software desarrollado.
