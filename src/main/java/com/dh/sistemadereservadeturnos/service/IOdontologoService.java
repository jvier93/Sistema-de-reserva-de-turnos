package com.dh.sistemadereservadeturnos.service;

import com.dh.sistemadereservadeturnos.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardar(Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Odontologo odontologo);

    Odontologo buscarPorMatricula(String matricula);

}
