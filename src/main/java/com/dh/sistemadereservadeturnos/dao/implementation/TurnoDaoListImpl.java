package com.dh.sistemadereservadeturnos.dao.implementation;

import com.dh.sistemadereservadeturnos.dao.IDao;
import com.dh.sistemadereservadeturnos.model.Turno;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDaoListImpl implements IDao<Turno> {

    private final Logger LOGGER = Logger.getLogger(TurnoDaoListImpl.class);


    private List<Turno> turnoList = new ArrayList<>();


    @Override
    public Turno guardar(Turno turno) {

        Turno turnoCreado = null;

        try {
            turnoList.add(turno);
            LOGGER.info("Se creo un turno");
            turnoCreado = turno;
        } catch (Exception e) {
            LOGGER.error("Error al crear nuevo turno: " + e.getMessage());
        }


        return turnoCreado;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoList;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoEncontrado = null;
        for (Turno t : turnoList) {
            if (t.getId().equals(id)) ;
            turnoEncontrado = t;
        }
        return turnoEncontrado;
    }

    @Override
    public void eliminar(Integer id) {
        turnoList.remove(buscarPorId(id));
    }

    @Override
    public void actualizar(Turno turno) {

    }
}
