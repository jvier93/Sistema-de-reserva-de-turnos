package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.dao.implementation.DomicilioDaoH2Impl;
import com.dh.sistemadereservadeturnos.model.Domicilio;
import com.dh.sistemadereservadeturnos.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {

    IDao<Domicilio> idao;

    public DomicilioService() {
        this.idao = new DomicilioDaoH2Impl();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return idao.guardar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return null;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Domicilio domicilio) {

    }
}
