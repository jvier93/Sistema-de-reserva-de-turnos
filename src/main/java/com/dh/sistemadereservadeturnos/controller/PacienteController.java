package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.model.Odontologo;
import com.dh.sistemadereservadeturnos.model.Paciente;
import com.dh.sistemadereservadeturnos.service.implementation.OdontologoService;
import com.dh.sistemadereservadeturnos.service.implementation.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private static final Logger LOGGER = Logger.getLogger(PacienteController.class);


    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    public Paciente guardar(@RequestBody Paciente paciente) {
        LOGGER.info(paciente);
        return pacienteService.guardar(paciente);
    }

}
