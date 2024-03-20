package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.entity.Turno;
import com.dh.sistemadereservadeturnos.service.implementation.OdontologoService;
import com.dh.sistemadereservadeturnos.service.implementation.PacienteService;
import com.dh.sistemadereservadeturnos.service.implementation.TurnoService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }


    @PostMapping("/guardar")

    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {

        ResponseEntity<Turno> response;

        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null || pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

}
