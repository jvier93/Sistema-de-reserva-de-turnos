package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.exception.ResourceNotFoundException;
import com.dh.sistemadereservadeturnos.repository.IPacienteRepository;
import com.dh.sistemadereservadeturnos.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IPacienteService {

    private static Logger logger = Logger.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();

    }

    @Override
    public Paciente buscarPorId(Long id) {

        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if (pacienteBuscado.isEmpty()){
            logger.error("Paciente no encontrado con id: " +id);
            throw new ResourceNotFoundException("Paciente no encontrado con id: " +id);
        }
        return pacienteBuscado.get();

    }

    @Override
    public void eliminar(Long id) {

        Optional<Paciente> pacienteAEliminar = pacienteRepository.findById(id);
        if (pacienteAEliminar.isEmpty()){
            logger.error("Paciente a eliminar no encontrado con id: " +id);
            throw new ResourceNotFoundException("Paciente a eliminar no encontrado con id: " +id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(Paciente paciente) {

        Optional<Paciente> pacienteAActualizar = pacienteRepository.findById(paciente.getId());
     if (pacienteAActualizar.isEmpty()){
         logger.error("Paciente a actualizar no encontrado con id: " +paciente.getId());
         throw new ResourceNotFoundException("Paciente a actualizar no encontrado con id: " +paciente.getId());
     }
     pacienteRepository.save(paciente);


    }
}
