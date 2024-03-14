package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.dao.implementation.PacienteDaoH2Impl;
import com.dh.sistemadereservadeturnos.model.Odontologo;
import com.dh.sistemadereservadeturnos.model.Paciente;
import com.dh.sistemadereservadeturnos.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteService implements IPacienteService {

    private IDao<Paciente> iDao;

    public PacienteService(PacienteDaoH2Impl iDao) {
        this.iDao = iDao;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return iDao.guardar(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Paciente paciente) {

    }
}
