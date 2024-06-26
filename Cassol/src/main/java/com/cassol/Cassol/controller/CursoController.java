package com.cassol.Cassol.controller;

import com.cassol.Cassol.entity.Curso;
import com.cassol.Cassol.exception.NotFoundException;
import com.cassol.Cassol.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obterCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.obterCursoPorId(id)
                .orElse(null);

        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.criarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        Optional<Curso> cursoOptional = cursoService.obterCursoPorId(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            return ResponseEntity.ok(curso);
        } else {
            throw new NotFoundException("Curso não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/media")
    public ResponseEntity<String> calcularMediaDoCurso(@PathVariable Long id) {
        Double media = cursoService.calcularMediaDoCurso(id);
        String mensagemMediaCurso = String.format("A média do curso é %.2f ", media);
        return ResponseEntity.ok(mensagemMediaCurso);
    }
}
