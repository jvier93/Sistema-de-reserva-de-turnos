package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.service.implementation.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);


    private OdontologoService odontologoService;


    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public Odontologo guardar(@RequestBody Odontologo odontologo) {
        return odontologoService.guardar(odontologo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok("Se actualizo el odontologo con id " + odontologo.getId());
        } else {
            return ResponseEntity.ok("No se puede actualizar el odontologo");
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula) throws Exception {
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorMatricula(matricula);

        if (odontologoOptional.isPresent()) {
            return ResponseEntity.ok(odontologoOptional.get());
        } else {
            throw new Exception("No se encontro el ondotolog con matricula" + matricula);
        }


    }


}
