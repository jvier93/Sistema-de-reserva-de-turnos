package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import com.dh.sistemadereservadeturnos.model.Paciente;
import com.dh.sistemadereservadeturnos.model.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TurnoServiceTest {


    @BeforeEach
    public void setUpDB() {
        H2DB.crearTablas();
    }

    @Test
    public void testGuardarTurno() {

        //DADO
        Domicilio domicilio = new Domicilio("Calle 1", 123, "Localidad 1", "Provincia 1");
        Paciente paciente = new Paciente("Juan", "Perez", "1234", LocalDate.now(), domicilio);
        PacienteService pacienteService = new PacienteService();
        Paciente pacienteGuardado = pacienteService.guardar(paciente);

        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);
        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        Turno turno = new Turno(1, odontologoGuardado, pacienteGuardado, LocalDate.now());
        TurnoService turnoService = new TurnoService();


        //CUANDO
        Turno turnoGuardado = turnoService.guardar(turno);

        //ENTONCES
        assertNotNull(turnoGuardado);


    }


}