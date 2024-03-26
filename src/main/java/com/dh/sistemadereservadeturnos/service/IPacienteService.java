package com.dh.sistemadereservadeturnos.service;

import com.dh.sistemadereservadeturnos.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente guardar(Paciente paciente);

    List<Paciente> listarTodos();

    Paciente buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Paciente paciente);


}
