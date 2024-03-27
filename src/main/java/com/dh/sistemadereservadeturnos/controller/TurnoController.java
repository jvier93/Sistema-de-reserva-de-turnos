package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
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

    @GetMapping("/{id}")
    public  ResponseEntity<Turno> buscarPorId(@PathVariable Long id){
        Turno turnoBuscado  = turnoService.buscarPorId(id);
        return ResponseEntity.ok(turnoBuscado);

    }


    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {

       Turno turnoGuardado = turnoService.guardar(turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoGuardado);


    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

            turnoService.eliminar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Turno turno){

            turnoService.actualizar(turno);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
