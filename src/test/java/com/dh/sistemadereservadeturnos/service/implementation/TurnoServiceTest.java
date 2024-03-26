package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Domicilio;
import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.entity.Turno;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @Test
    public void testListarTurnos(){
        //DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setLocalidad("localidad1");
        domicilio.setNumero(1);
        domicilio.setProvincia("provincia1");


        Paciente paciente = new Paciente();
        paciente.setNombre("nombre");
        paciente.setApellido("apellido");
        paciente.setDni("123");
        paciente.setDomicilio(domicilio);
        paciente.setFechaIngreso(LocalDate.now());


        pacienteService.guardar(paciente);
        Paciente pacienteGuardado = pacienteService.guardar(paciente);


        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now());
        turno.setHora(LocalTime.now());

        Turno turnoGuardado = turnoService.guardar(turno);

        //CUANDO
        List<Turno> turnosEncontrados = turnoService.listar();

        //ENTONCES
        assertEquals(1, turnosEncontrados.size());


    }
    @Test
    @Transactional
    public void testGuardarTurno() {

        //DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setLocalidad("localidad1");
        domicilio.setNumero(1);
        domicilio.setProvincia("provincia1");


        Paciente paciente = new Paciente();
        paciente.setNombre("nombre");
        paciente.setApellido("apellido");
        paciente.setDni("123");
        paciente.setDomicilio(domicilio);
        paciente.setFechaIngreso(LocalDate.now());


        pacienteService.guardar(paciente);
        Paciente pacienteGuardado = pacienteService.guardar(paciente);


        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now());
        turno.setHora(LocalTime.now());



        //CUANDO
        Turno turnoGuardado = turnoService.guardar(turno);

        //ENTONCES
        assertNotNull(turnoGuardado);


    }

    @Test
    public void testEliminarTurno(){

        //DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setLocalidad("localidad1");
        domicilio.setNumero(1);
        domicilio.setProvincia("provincia1");


        Paciente paciente = new Paciente();
        paciente.setNombre("nombre");
        paciente.setApellido("apellido");
        paciente.setDni("123");
        paciente.setDomicilio(domicilio);
        paciente.setFechaIngreso(LocalDate.now());


        pacienteService.guardar(paciente);
        Paciente pacienteGuardado = pacienteService.guardar(paciente);


        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        Turno turno = new Turno();
        turno.setPaciente(pacienteGuardado);
        turno.setOdontologo(odontologoGuardado);
        turno.setFecha(LocalDate.now());
        turno.setHora(LocalTime.now());

        Turno turnoGuardado = turnoService.guardar(turno);


        //CUANDO
        turnoService.eliminar(turnoGuardado.getId());
        Turno turnoEliminado = turnoService.buscarPorId(turnoGuardado.getId());


        //ENTONCES
        assertNull(turnoEliminado);
    }



    @Test
    @Transactional
    public void testBuscarTurnoPorId(){


        //DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setLocalidad("localidad1");
        domicilio.setNumero(1);
        domicilio.setProvincia("provincia1");


        Paciente paciente = new Paciente();
        paciente.setNombre("nombre");
        paciente.setApellido("apellido");
        paciente.setDni("123");
        paciente.setDomicilio(domicilio);
        paciente.setFechaIngreso(LocalDate.now());


        pacienteService.guardar(paciente);
        Paciente pacienteGuardado = pacienteService.guardar(paciente);


        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now());
        turno.setHora(LocalTime.now());

        Turno turnoGuardado = turnoService.guardar(turno);

        //CUANDO
        Turno turnoEncontrado = turnoService.buscarPorId(turnoGuardado.getId());

        //ENTONCES
        assertNotNull(turnoEncontrado);
    }

    @Test
    @Transactional
    public void testActualizarTurno(){
        //DADO
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setLocalidad("localidad1");
        domicilio.setNumero(1);
        domicilio.setProvincia("provincia1");


        Paciente paciente = new Paciente();
        paciente.setNombre("nombre");
        paciente.setApellido("apellido");
        paciente.setDni("123");
        paciente.setDomicilio(domicilio);
        paciente.setFechaIngreso(LocalDate.now());


        pacienteService.guardar(paciente);
        Paciente pacienteGuardado = pacienteService.guardar(paciente);


        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(LocalDate.now());
        turno.setHora(LocalTime.now());

        Turno turnoGuardado = turnoService.guardar(turno);
        turnoGuardado.setFecha(LocalDate.of(2024, 8, 12));

        //CUANDO
        turnoService.actualizar(turnoGuardado);
        Turno turnoActualizado = turnoService.buscarPorId(turnoGuardado.getId());

        //ENTONCES
        LocalDate fechaEsperada = LocalDate.of(2024, 8, 12);

        assertEquals(fechaEsperada,  turnoActualizado.getFecha());

    }




}