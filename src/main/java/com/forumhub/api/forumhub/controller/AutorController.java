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

    @GetMapping
    public ResponseEntity<Page<DadosAutor>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        // Trocamos 'DadosListagemAutor' por 'DadosAutor'
        var pagina = autorRepository.findAllByAtivoTrue(pageable).map(DadosAutor::new);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var autor = autorRepository.getReferenceById(id);

        // Proteção para não mostrar autor excluído logicamente
        if (!autor.getAtivo()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DadosAutor(autor));
    }

    @PostMapping
    public Autor cadastrar(@RequestBody @Valid Autor a) {
        return autorRepository.save(a);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editar(@PathVariable Long id, @RequestBody @Valid DadosAutor dados) {
        var autor = autorRepository.getReferenceById(id);

        autor.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosAutor(autor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        var autor = autorRepository.getReferenceById(id);
        autor.excluir(); // Isso muda o status para false no banco

        return ResponseEntity.noContent().build();
    }

}
