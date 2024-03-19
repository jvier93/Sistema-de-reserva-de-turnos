package com.dh.sistemadereservadeturnos.repository;

import com.dh.sistemadereservadeturnos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
