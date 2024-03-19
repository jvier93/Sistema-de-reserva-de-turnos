package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.service.implementation.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.ok("Paciente eliminado con exito");
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {

        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizar(paciente);
            return ResponseEntity.ok("Se actualizo el paciente con id " + paciente.getId());
        } else {
            return ResponseEntity.ok("No se puede actualizar el paciente");
        }
    }

}
