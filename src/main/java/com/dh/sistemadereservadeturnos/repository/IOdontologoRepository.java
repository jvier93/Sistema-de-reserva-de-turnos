package com.dh.sistemadereservadeturnos.repository;


import com.dh.sistemadereservadeturnos.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Optional<Odontologo> buscarPorMatricula(String matricula);

}
