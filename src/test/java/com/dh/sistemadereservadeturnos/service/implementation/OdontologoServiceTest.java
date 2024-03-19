package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
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
    public void testGuardarOdontologo() {


        //DADO
        Odontologo odontologo = new Odontologo();


        //CUANDO
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //ENTONCES
        assertNotNull(odontologoGuardado);


    }

    @Test
    public void testListarTodosOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo();


        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        List<Odontologo> odontologosEncontrados = odontologoService.listarTodos();

        //ENTONCES
        assertEquals(1, odontologosEncontrados.size());
    }

    @Test
    public void testBuscarOdontologoPorId() {
        //DADO
        Odontologo odontologo = new Odontologo();

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(1L);

        //ENTONCES
        assertNotNull(odontologoEncontrado);
    }

    @Test
    public void testEliminarOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo();

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        odontologoService.eliminar(1L);
        Odontologo odontologoEliminado = odontologoService.buscarPorId(1L);

        //ENTONCES
        assertNull(odontologoEliminado);

    }

    @Test
    public void testActualizarOdontologo() {

        //DADO
        Odontologo odontologo = new Odontologo();

        //Debemos conocer el id del odontologo que queremos actualizar.
        Odontologo odontologo2 = new Odontologo();
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        odontologoService.actualizar(odontologo2);
        Odontologo odontologoActualizado = odontologoService.buscarPorId(1L);

        //ENTONCES
        assertEquals("Jose", odontologoActualizado);

    }

}