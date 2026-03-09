package com.forumhub.api.forumhub.controller;

import com.forumhub.api.forumhub.domain.autor.Autor;
import com.forumhub.api.forumhub.domain.autor.DadosAutor;
import com.forumhub.api.forumhub.repository.AutorRepository;
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
@RequestMapping("/autores")
@SecurityRequirement(name = "bearer-key")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/{id}")
    public ResponseEntity buscarAutor(@PathVariable Long id) {
        var autor = autorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosAutor(autor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosAutor>> listarAutores(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {

        var pagina = autorRepository.findAll(pageable).map(DadosAutor::new);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    public Autor cadastrar(@RequestBody @Valid Autor a) {
        return autorRepository.save(a);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editar(@PathVariable Long id, @RequestBody @Valid DadosAutor dados) {
        // 1. Carrega o autor que já existe no banco
        var autor = autorRepository.getReferenceById(id);

        // 2. Atualiza os campos usando o método que criamos na entidade
        autor.atualizarInformacoes(dados);

        // 3. Retorna o autor atualizado (convertido para o DTO de detalhamento ou o
        // próprio Record)
        return ResponseEntity.ok(new DadosAutor(autor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        autorRepository.deleteById(id);
    }

}
