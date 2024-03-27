package com.dh.sistemadereservadeturnos.service.implementation;

import com.dh.sistemadereservadeturnos.entity.Odontologo;
import com.dh.sistemadereservadeturnos.exception.ResourceNotFoundException;
import com.dh.sistemadereservadeturnos.repository.IOdontologoRepository;
import com.dh.sistemadereservadeturnos.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private static Logger logger = Logger.getLogger(OdontologoService.class);

    private IOdontologoRepository odontologoRepository;


    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {

       Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);

       if(odontologoBuscado.isEmpty()){
           logger.error("Odontologo no encontrado con id: " +id);
           throw new ResourceNotFoundException("Odontologo no encontrado con id: " +id);
       }
     return odontologoBuscado.get();
    }

    @Override
    public void eliminar(Long id) {

        Optional<Odontologo> odontologoAEliminar = odontologoRepository.findById(id);
        if(odontologoAEliminar.isEmpty()){
            logger.error("Odontologo a eliminar no encontrado con id: " +id);
            throw new ResourceNotFoundException("Odontologo a eliminar no encontrado con id: " +id);
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologoAActualizar = odontologoRepository.findById(odontologo.getId());
        if(odontologoAActualizar.isEmpty()){
            logger.error("Odontologo a actualizar no encontrado con id: " +odontologo.getId());
            throw new ResourceNotFoundException("Odontologo a actualizar no encontrado con id: " +odontologo.getId());
        }

        odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorMatricula(String matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.buscarPorMatricula(matricula);
        if (odontologoBuscado.isEmpty()){
            logger.error("Odontologo no encontrado con matricula: " +matricula);
            throw new ResourceNotFoundException("Odontologo no encontrado con matricula: " +matricula);
        }
      return odontologoBuscado.get();
    }
}
