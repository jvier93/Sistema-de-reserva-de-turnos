package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.entity.Paciente;
import com.dh.sistemadereservadeturnos.repository.IPacienteRepository;
import com.dh.sistemadereservadeturnos.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PacienteService implements IPacienteService {

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

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            return paciente;
        } else {
            return null;
        }

    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(Paciente paciente) {

    }
}
