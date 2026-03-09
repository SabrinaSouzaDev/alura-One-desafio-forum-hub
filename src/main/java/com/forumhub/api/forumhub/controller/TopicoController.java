package com.forumhub.api.forumhub.controller;

import com.forumhub.api.forumhub.domain.topico.DadosCadastroTopico;
import com.forumhub.api.forumhub.domain.topico.DadosDetalhamentoTopico;
import com.forumhub.api.forumhub.domain.topico.Topico;
import com.forumhub.api.forumhub.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder builder) {
        var topico = new Topico(dados);
        topicoRepository.save(topico);

        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body((new DadosDetalhamentoTopico(topico)));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);

        // Se o tópico estiver inativo, retorna 404 para o Swagger
        if (!topico.getAtivo()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listarTopicos(Pageable pageable) {
        // Usamos o método que criamos no Repository para filtrar apenas os ativos
        var pagina = topicoRepository.findAllByAtivoTrue(pageable)
                .map(DadosDetalhamentoTopico::new);
        return ResponseEntity.ok(pagina);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroTopico dados) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        if (!topico.getAtivo()) {
            return ResponseEntity.notFound().build();
        }

        topico.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // Agora o 'topicoRepository' será reconhecido pelo Java
        Topico topico = topicoRepository.getReferenceById(id);

        topico.excluir(); // Método que criamos na classe Topico

        return ResponseEntity.noContent().build();
    }

}
