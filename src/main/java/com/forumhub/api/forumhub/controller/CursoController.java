package com.forumhub.api.forumhub.controller;

import com.forumhub.api.forumhub.domain.curso.Curso;
import com.forumhub.api.forumhub.domain.curso.DadosCurso;
import com.forumhub.api.forumhub.repository.CursoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/{id}")
    public ResponseEntity buscarAutor(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosCurso>> listarCursos(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        var pagina = cursoRepository.findAll(pageable).map(DadosCurso::new);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    public Curso cadastrar(@RequestBody @Valid Curso a) {
        return cursoRepository.save(a);
    }

    @PutMapping()
    public Curso editar(@RequestBody @Valid Curso a) {
        return cursoRepository.save(a);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }
}
