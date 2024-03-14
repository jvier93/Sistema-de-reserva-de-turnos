package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.dao.implementation.TurnoDaoListImpl;
import com.dh.sistemadereservadeturnos.model.Turno;
import com.dh.sistemadereservadeturnos.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TurnoService implements ITurnoService {

    private IDao<Turno> iDao;


    public TurnoService(TurnoDaoListImpl iDao) {
        this.iDao = iDao;
    }

    @Override
    public Turno guardar(Turno turno) {
        return iDao.guardar(turno);
    }
}
