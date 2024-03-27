package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.service.implementation.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private OdontologoService odontologoService;


    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        Odontologo odontologoGuardado = odontologoService.guardar(odontologo);
        return  ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        Odontologo odontologoBuscado = odontologoService.buscarPorId(id);
        return ResponseEntity.ok(odontologoBuscado);

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            odontologoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {

            odontologoService.actualizar(odontologo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula) throws Exception {

        Odontologo odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        return ResponseEntity.ok(odontologoBuscado);


    }


}
