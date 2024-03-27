package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.service.implementation.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/paciente")
public class PacienteController {



    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        Paciente pacienteGuardado = pacienteService.guardar(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {

        Paciente pacienteBuscado  = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(pacienteBuscado);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            pacienteService.eliminar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {


            pacienteService.actualizar(paciente);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
