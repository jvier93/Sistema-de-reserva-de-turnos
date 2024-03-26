package com.dh.sistemadereservadeturnos.service;

import com.dh.sistemadereservadeturnos.entity.Turno;

import java.util.List;
import java.util.Set;

public interface ITurnoService {

    Turno guardar(Turno turno);

    void eliminar(Long id);

    List<Turno> listar();

    void actualizar(Turno turno);

    Turno buscarPorId(Long id);

}
