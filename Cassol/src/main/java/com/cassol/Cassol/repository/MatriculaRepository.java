package com.cassol.Cassol.repository;

import com.cassol.Cassol.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByCursoId(Long idCurso);
}
