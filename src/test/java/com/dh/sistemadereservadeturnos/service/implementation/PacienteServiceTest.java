package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {


    @BeforeEach
    public void setUpDB() {
        H2DB.crearTablas();
    }

    @Test
    public void testGuardarPaciente() {

        //DADO
        Domicilio domicilio = new Domicilio("Calle 1", 123, "Localidad 1", "Provincia 1");
        Paciente paciente = new Paciente("Juan", "Perez", "1234", LocalDate.now(), domicilio);
        PacienteService pacienteService = new PacienteService();


        //CUANDO
        Paciente pacienteGuardado = pacienteService.guardar(paciente);

        //ENTONCES
        assertNotNull(pacienteGuardado);
    }

    @Test
    public void testBuscarPacientePorId() {

        //DADO
        Domicilio domicilio = new Domicilio("Reyles", 103, "Salto", "Salto");
        Paciente paciente = new Paciente("Juan", "Perez", "1234", LocalDate.now(), domicilio);
        PacienteService pacienteService = new PacienteService();
        pacienteService.guardar(paciente);

        //CUANDO
        Paciente pacienteBuscado = pacienteService.buscarPorId(1);

        //ENTONCES
        assertNotNull(pacienteBuscado);

    }

}