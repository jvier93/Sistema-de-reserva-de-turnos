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
        if(turnoBuscado != null){
            return ResponseEntity.ok(turnoBuscado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {

        ResponseEntity<Turno> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
        Paciente pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());

        if (odontologoBuscado != null || pacienteBuscado != null) {
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        Turno turnoBuscado = turnoService.buscarPorId(id);
        if(turnoBuscado != null){
            turnoService.eliminar(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Turno turno){
        Turno turnoBuscado = turnoService.buscarPorId(turno.getId());
        Odontologo odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
        Paciente pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());


        if(turnoBuscado != null && odontologoBuscado != null && pacienteBuscado != null ){
            turnoService.actualizar(turno);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
