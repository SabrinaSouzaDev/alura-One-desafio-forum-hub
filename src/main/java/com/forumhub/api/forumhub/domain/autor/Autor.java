package com.forumhub.api.forumhub.domain.autor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity(name = "Autor")
@Table(name = "autores", schema = "forumhub_schema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Boolean ativo = true;

    public Autor(DadosAutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();

    }

    public void atualizarInformacoes(DadosAutor dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

}
