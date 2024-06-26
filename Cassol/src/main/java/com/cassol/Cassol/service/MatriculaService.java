package com.cassol.Cassol.service;

import com.cassol.Cassol.entity.Matricula;
import com.cassol.Cassol.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public List<Matricula> listarMatriculas() {
        return matriculaRepository.findAll();
    }

    public Optional<Matricula> obterMatriculaPorId(Long id) {
        return matriculaRepository.findById(id);
    }

    public Matricula criarMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public Matricula atualizarNotaMatricula(Long id, Double novaNota) {
        Optional<Matricula> matriculaExistente = matriculaRepository.findById(id);
        if (matriculaExistente.isPresent()) {
            Matricula matricula = matriculaExistente.get();
            matricula.setNota(novaNota);
            return matriculaRepository.save(matricula);
        }
        return null;
    }

    public void deletarMatricula(Long id) {
        matriculaRepository.deleteById(id);
    }
}
