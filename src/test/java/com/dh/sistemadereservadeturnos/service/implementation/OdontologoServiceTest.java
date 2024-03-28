package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.exception.ResourceNotFoundException;
import com.dh.sistemadereservadeturnos.repository.IOdontologoRepository;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

    private Odontologo odontologo;
    @BeforeEach
    public void setUp(){

        //Instanciamos variables necesarias
        odontologo = new Odontologo();
        odontologo.setNombre("nombre");
        odontologo.setApellido("apellido");
        odontologo.setMatricula(123);
    }


    @Test
    @Transactional
    public void testGuardarOdontologo() {


        //DADO


        //CUANDO
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //ENTONCES
        assertNotNull(odontologoGuardado);


    }

    @Test
    public void testListarTodosOdontologo() {
  //DADO
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
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologoGuardado.getId());

        //ENTONCES
        assertNotNull(odontologoEncontrado);
    }

    @Test
    public void testBuscarOdontologoInexistentePorId() {
        //DADO
        odontologoService.guardar(odontologo);

        //CUANDO
        //ENTONCES
        assertThrows(ResourceNotFoundException.class,()->{odontologoService.buscarPorId(1223L);});
    }

    @Test()
    public void testEliminarOdontologo() {
        //DADO
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

        //CUANDO
        odontologoService.eliminar(odontologoGuardado.getId());

        //ENTONCES

       assertThrows(ResourceNotFoundException.class,()->{odontologoService.buscarPorId(odontologoGuardado.getId());});

    }

    @Test
    @Transactional
    public void testActualizarOdontologo() {

        //DADO
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        odontologoGuardado.setNombre("Jose");

        //CUANDO
        odontologoService.actualizar(odontologoGuardado);
        Odontologo odontologoActualizado = odontologoService.buscarPorId(odontologoGuardado.getId());

        //ENTONCES
        assertEquals("Jose", odontologoActualizado.getNombre());

    }



}