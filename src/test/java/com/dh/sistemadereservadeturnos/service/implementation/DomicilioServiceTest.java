package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomicilioServiceTest {

    @BeforeEach
    public void setUpDB() {
        H2DB.crearTablas();
    }

    @Test
    public void testGuardarDomicilio() {


        //DADO
        Domicilio domicilio = new Domicilio("Calle 1", 123, "Localidad 1", "Provincia 1");
        DomicilioService domicilioService = new DomicilioService();


        //CUANDO
        Domicilio domicilioGuardado = domicilioService.guardar(domicilio);

        //ENTONCES
        assertNotNull(domicilioGuardado);

    }


}