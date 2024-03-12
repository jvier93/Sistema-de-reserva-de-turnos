package com.dh.sistemadereservadeturnos.controller;

import com.dh.sistemadereservadeturnos.dao.H2DB;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import com.dh.sistemadereservadeturnos.service.IOdontologoService;
import com.dh.sistemadereservadeturnos.service.implementation.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @PutMapping
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
}
