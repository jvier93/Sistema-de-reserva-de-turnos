package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.entity.Turno;
import com.dh.sistemadereservadeturnos.exception.BadRequestException;
import com.dh.sistemadereservadeturnos.exception.ResourceNotFoundException;
import com.dh.sistemadereservadeturnos.repository.IOdontologoRepository;
import com.dh.sistemadereservadeturnos.repository.IPacienteRepository;
import com.dh.sistemadereservadeturnos.repository.ITurnoRepository;
import com.dh.sistemadereservadeturnos.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TurnoService implements ITurnoService {

    private Logger logger = Logger.getLogger(TurnoService.class);

    private ITurnoRepository turnoRepository;
    private IOdontologoRepository odontologoRepository;

    private IPacienteRepository pacienteRepository;


    public TurnoService(ITurnoRepository turnoRepository, IPacienteRepository pacienteRepository, IOdontologoRepository odontologoRepository ) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }


    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    @Override
    public void actualizar(Turno turno) {
        Optional<Turno> turnoAActualizar = turnoRepository.findById(turno.getId());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo().getId());
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
        if (turnoAActualizar.isEmpty() || odontologoBuscado.isEmpty() || pacienteBuscado.isEmpty()){
            logger.error("Alguno de los datos solicitados no existen para el turno: " +turno.getId());
            throw new BadRequestException("Alguno de los datos solicitados no existen para el turno: " +turno.getId());
        }
        turnoRepository.save(turno);
    }

    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isEmpty()){
            logger.error("Turno no encontrado con id: " +id);
            throw new ResourceNotFoundException("Turno no encontrado con id: " +id);
        }
      return turnoBuscado.get();
    }

    @Override
    public Turno guardar(Turno turno) {

        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo().getId());
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
        if (odontologoBuscado.isEmpty() || pacienteBuscado.isEmpty()){
            logger.error("Alguno de los datos solicitados no existe");
            throw new BadRequestException("Alguno de los datos solicitados no existen");
        }
        return turnoRepository.save(turno);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Turno> turnoAELiminar = turnoRepository.findById(id);
        if (turnoAELiminar.isEmpty()){
            logger.error("Turno a eliminar no encontrado con id: " +id);
            throw new ResourceNotFoundException("Turno a eliminar no encontrado con id: " +id);
        }
        turnoRepository.deleteById(id);
    }




}
