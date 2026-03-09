package com.forumhub.api.forumhub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table(name = "cursos", schema = "forumhub_schema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private Boolean ativo = true;
    
    public Curso(DadosCurso dados){
        this.nome = dados.nome();
        this.categoria = dados.categoriaCurso();
    }

    // Método para atualizar os dados com segurança
    public void atualizarInformacoes(DadosCurso dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.categoriaCurso() != null) {
            this.categoria = dados.categoriaCurso();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
