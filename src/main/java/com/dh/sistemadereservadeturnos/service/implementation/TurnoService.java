package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.dao.implementation.TurnoDaoListImpl;
import com.dh.sistemadereservadeturnos.model.Turno;
import com.dh.sistemadereservadeturnos.service.ITurnoService;
import org.springframework.stereotype.Service;


@Service
public class TurnoService implements ITurnoService {

    private IDao<Turno> iDao;


    public TurnoService() {
        this.iDao = new TurnoDaoListImpl();

    }

    @Override
    public Turno guardar(Turno turno) {
        return iDao.guardar(turno);
    }
}
