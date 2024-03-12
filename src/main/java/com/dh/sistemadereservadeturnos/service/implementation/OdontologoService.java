package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.dao.implementation.OdontologoDaoH2Impl;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import com.dh.sistemadereservadeturnos.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDao;


    //Implementamos odontologo service directamente con la implementacion de H2.
    public OdontologoService() {
        this.iDao = new OdontologoDaoH2Impl();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void eliminar(Integer id) {
        iDao.eliminar(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        iDao.actualizar(odontologo);
    }
}
