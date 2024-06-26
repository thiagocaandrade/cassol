package com.cassol.Cassol.service;

import com.cassol.Cassol.entity.Curso;
import com.cassol.Cassol.entity.Matricula;
import com.cassol.Cassol.repository.CursoRepository;
import com.cassol.Cassol.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final MatriculaRepository matriculaRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository, MatriculaRepository matriculaRepository) {
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obterCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizarCurso(Long id, Curso cursoAtualizado) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get();
            curso.setNome(cursoAtualizado.getNome());
            curso.setDescricao(cursoAtualizado.getDescricao());
            return cursoRepository.save(curso);
        }
        return null;
    }

    public void deletarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Double calcularMediaDoCurso(Long idCurso) {
        List<Matricula> matriculas = matriculaRepository.findByCursoId(idCurso);
        if (matriculas.isEmpty()) {
            return 0.0;
        }

        double somaNotas = matriculas.stream()
                .mapToDouble(Matricula::getNota)
                .sum();

        return somaNotas / matriculas.size();
    }
}
