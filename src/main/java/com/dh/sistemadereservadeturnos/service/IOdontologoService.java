package com.dh.sistemadereservadeturnos.service;

import com.dh.sistemadereservadeturnos.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    com.dh.sistemadereservadeturnos.model.Odontologo guardar(Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);

    void eliminar(Integer id);

    void actualizar(Odontologo odontologo);

}
