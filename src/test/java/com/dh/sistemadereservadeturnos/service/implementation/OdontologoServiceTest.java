package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @BeforeEach
    public void setUpDB() {
        H2DB.crearTablas();
    }


    @Test
    public void testGuardarOdontologo() {


        //DADO
        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);


        //CUANDO
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //ENTONCES
        assertNotNull(odontologoGuardado);


    }

    @Test
    public void testListarTodosOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        List<Odontologo> odontologosEncontrados = odontologoService.listarTodos();

        //ENTONCES
        assertEquals(1, odontologosEncontrados.size());
    }

    @Test
    public void testBuscarOdontologoPorId() {
        //DADO
        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(1);

        //ENTONCES
        assertNotNull(odontologoEncontrado);
    }

    @Test
    public void testEliminarOdontologo() {
        //DADO
        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);

        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        odontologoService.eliminar(1);
        Odontologo odontologoEliminado = odontologoService.buscarPorId(1);

        //ENTONCES
        assertNull(odontologoEliminado);

    }

    @Test
    public void testActualizarOdontologo() {

        //DADO
        Odontologo odontologo = new Odontologo("Pepe", "Perez", 1);

        //Debemos conocer el id del odontologo que queremos actualizar.
        Odontologo odontologo2 = new Odontologo(1, "Jose", "Perez", 1);
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        odontologoService.actualizar(odontologo2);
        Odontologo odontologoActualizado = odontologoService.buscarPorId(1);

        //ENTONCES
        assertEquals("Jose", odontologoActualizado.getNombre());

    }

}