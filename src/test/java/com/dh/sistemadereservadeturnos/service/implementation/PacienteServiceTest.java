package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @BeforeEach
    public void setUpDB() {
        H2DB.crearTablas();
    }

    @Test
    public void testGuardarPaciente() {

        //DADO
        Domicilio domicilio = new Domicilio("Calle 1", 123, "Localidad 1", "Provincia 1");
        Paciente paciente = new Paciente("Juan", "Perez", "1234", LocalDate.now(), domicilio);


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
        pacienteService.guardar(paciente);

        //CUANDO
        Paciente pacienteBuscado = pacienteService.buscarPorId(1);

        //ENTONCES
        assertNotNull(pacienteBuscado);

    }

}