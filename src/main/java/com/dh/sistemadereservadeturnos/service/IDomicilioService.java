package com.dh.sistemadereservadeturnos.service;

import com.dh.sistemadereservadeturnos.entity.Domicilio;

import java.util.List;

public interface IDomicilioService {


    Domicilio guardar(Domicilio domicilio);

    List<Domicilio> listarTodos();

    Domicilio buscarPorId(Long id);

    void eliminar(Integer id);

    void actualizar(Domicilio domicilio);


}
