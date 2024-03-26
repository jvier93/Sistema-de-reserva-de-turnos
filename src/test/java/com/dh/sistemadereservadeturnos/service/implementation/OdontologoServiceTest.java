package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.repository.IOdontologoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;




    @Test
    @Transactional
    public void testGuardarOdontologo() {


        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);


        //CUANDO
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //ENTONCES
        assertNotNull(odontologoGuardado);


    }

    @Test
    public void testListarTodosOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);


        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        List<Odontologo> odontologosEncontrados = odontologoService.listarTodos();

        //ENTONCES
        assertEquals(1, odontologosEncontrados.size());
    }

    @Test
    @Transactional
    public void testBuscarOdontologoPorId() {
        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologoGuardado.getId());

        //ENTONCES
        assertNotNull(odontologoEncontrado);
    }

    @Test
    public void testBuscarOdontologoInexistentePorId() {
        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        odontologoService.guardar(odontologo);

        //CUANDO
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(123L);

        //ENTONCES
        assertNull(odontologoEncontrado);
    }

    @Test
    public void testEliminarOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        odontologoService.guardar(odontologo);

        //CUANDO
        odontologoService.eliminar(1L);
        Odontologo odontologoEliminado = odontologoService.buscarPorId(1L);

        //ENTONCES
        assertNull(odontologoEliminado);

    }

    @Test
    @Transactional
    public void testActualizarOdontologo() {

        //DADO
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        odontologoGuardado.setNombre("Jose");

        //CUANDO
        odontologoService.actualizar(odontologoGuardado);
        Odontologo odontologoActualizado = odontologoService.buscarPorId(odontologoGuardado.getId());

        //ENTONCES
        assertEquals("Jose", odontologoActualizado.getNombre());

    }



}