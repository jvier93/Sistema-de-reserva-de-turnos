package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Domicilio;
import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    private Paciente paciente;
    private Domicilio domicilio;

    @BeforeEach
    public void setUp(){

        //Instanciamos variables necesarias
        domicilio = new Domicilio();
        domicilio.setCalle("calle1");
        domicilio.setLocalidad("localidad1");
        domicilio.setNumero(1);
        domicilio.setProvincia("provincia1");

        paciente = new Paciente();
        paciente.setNombre("nombre");
        paciente.setApellido("apellido");
        paciente.setDni("123");
        paciente.setDomicilio(domicilio);
        paciente.setFechaIngreso(LocalDate.now());

    }


    @Test
    @Transactional
    public void testGuardarPaciente() {

        //CUANDO
        Paciente pacienteGuardado = pacienteService.guardar(paciente);

        //ENTONCES
        assertNotNull(pacienteGuardado);
    }

    @Test
    @Transactional
    public void testBuscarPacientePorId() {

        //DADO
        Paciente pacienteGuardado =  pacienteService.guardar(paciente);

        //CUANDO
        Paciente pacienteBuscado = pacienteService.buscarPorId(pacienteGuardado.getId());

        //ENTONCES
        assertNotNull(pacienteBuscado);

    }

    @Test public void testEliminarPaciente(){

        //DADO
        Paciente pacienteGuardado = pacienteService.guardar(paciente);

        //CUANDO
        pacienteService.eliminar(pacienteGuardado.getId());


        //ENTONCES
        assertThrows(ResourceNotFoundException.class,()->{pacienteService.buscarPorId(pacienteGuardado.getId());});


    }

    @Test
    @Transactional
    public void testActualizarPaciente() {

        //DADO
        Paciente pacienteGuardado = pacienteService.guardar(paciente);
        pacienteGuardado.setNombre("Jose");

        //CUANDO
        pacienteService.actualizar(pacienteGuardado);
        Paciente pacienteObtenido  = pacienteService.buscarPorId(pacienteGuardado.getId());

        //ENTONCES
        assertEquals("Jose", pacienteObtenido.getNombre());

    }

    @Test
    public void testListarPacientes(){

        //DADO
        Paciente pacienteGuardado = pacienteService.guardar(paciente);

        //CUANDO
        List<Paciente> pacientesBuscados = pacienteService.listarTodos();

        //ENTONCES
        assertEquals(1, pacientesBuscados.size());

    }



}