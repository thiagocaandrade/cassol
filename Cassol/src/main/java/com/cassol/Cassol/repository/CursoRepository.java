package com.cassol.Cassol.repository;

import com.cassol.Cassol.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
