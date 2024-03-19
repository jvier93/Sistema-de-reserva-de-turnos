package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Domicilio;
import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.entity.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

//    @Autowired
//    private TurnoService turnoService;
//
//    @Autowired
//    private PacienteService pacienteService;
//    @Autowired
//    private OdontologoService odontologoService;
//
//
//    @Test
//    public void testGuardarTurno() {
//
//        //DADO
//        Domicilio domicilio = new Domicilio("Calle 1", 123, "Localidad 1", "Provincia 1");
//        Paciente paciente = new Paciente("Juan", "Perez", "1234", LocalDate.now(), domicilio);
//
//        Paciente pacienteGuardado = pacienteService.guardar(paciente);
//
//        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);
//
//        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
//
//        Turno turno = new Turno(1, odontologoGuardado, pacienteGuardado, LocalDate.now());
//
//
//        //CUANDO
//        Turno turnoGuardado = turnoService.guardar(turno);
//
//        //ENTONCES
//        assertNotNull(turnoGuardado);
//
//
//    }


}