package com.forumhub.api.forumhub.domain.autor;

public record DadosAutor(Long id, String nome, String email, Boolean ativo) {

public DadosAutor(Autor autor){
    this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getAtivo());
}
}
