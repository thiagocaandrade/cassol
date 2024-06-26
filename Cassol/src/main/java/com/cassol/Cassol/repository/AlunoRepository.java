package com.cassol.Cassol.repository;

import com.cassol.Cassol.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
