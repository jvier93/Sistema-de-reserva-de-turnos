package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Turno;
import com.dh.sistemadereservadeturnos.repository.ITurnoRepository;
import com.dh.sistemadereservadeturnos.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;


    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }
}
