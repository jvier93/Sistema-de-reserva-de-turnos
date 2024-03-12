package com.dh.sistemadereservadeturnos;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaDeReservaDeTurnosApplication {


    public static void main(String[] args) {
        H2DB.crearTablas();
        SpringApplication.run(SistemaDeReservaDeTurnosApplication.class, args);
    }

}
