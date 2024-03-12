package com.dh.sistemadereservadeturnos.service;

import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.model.Paciente;

import java.util.List;

public interface IDomicilioService {


    Domicilio guardar(Domicilio domicilio);

    List<Domicilio> listarTodos();

    Domicilio buscarPorId(Integer id);

    void eliminar(Integer id);

    void actualizar(Domicilio domicilio);


}
