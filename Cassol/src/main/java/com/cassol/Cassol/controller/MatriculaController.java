package com.cassol.Cassol.controller;

import com.cassol.Cassol.entity.Matricula;
import com.cassol.Cassol.exception.NotFoundException;
import com.cassol.Cassol.service.AlunoService;
import com.cassol.Cassol.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @Autowired
    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> listarMatriculas() {
        List<Matricula> matriculas = matriculaService.listarMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> obterMatriculaPorId(@PathVariable Long id) {
        Optional<Matricula> matriculaOptional = matriculaService.obterMatriculaPorId(id);

        if (matriculaOptional.isPresent()) {
            Matricula matricula = matriculaOptional.get();
            return ResponseEntity.ok(matricula);
        } else {
            throw new NotFoundException("Matrícula não encontrada com o ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Matricula> criarMatricula(@RequestBody Matricula matricula) {
        Matricula novaMatricula = matriculaService.criarMatricula(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMatricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizarNotaMatricula(@PathVariable Long id, @RequestBody Double novaNota) {
        Matricula matricula = matriculaService.atualizarNotaMatricula(id, novaNota);
        if (matricula != null) {
            return ResponseEntity.ok(matricula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        matriculaService.deletarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}
