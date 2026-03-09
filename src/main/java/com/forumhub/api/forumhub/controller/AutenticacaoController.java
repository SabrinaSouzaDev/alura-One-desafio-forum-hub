package com.forumhub.api.forumhub.controller;

import com.forumhub.api.forumhub.infra.security.TokenService;
import com.forumhub.api.forumhub.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Adicionado para o teste manual
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid UsuarioLoginDTO dados) {
        System.out.println(">>> NOVO HASH PARA O BANCO: " + new BCryptPasswordEncoder().encode("12345"));
        System.out.println(">>> 1. Chegou no Controller. Login: [" + dados.login() + "]");
        System.out.println(">>> Senha recebida: [" + dados.senha() + "]");

        // TESTE DE DEVOPS: Validação manual do Hash que está no seu banco
        String hashNoBanco = "$2a$10$Y50UaMFOxteibQEYfDj6oeYF78.N8r8p5J9s.HAnm1pXJ.IuVjBie";
        boolean senhaBateComHash = new BCryptPasswordEncoder().matches(dados.senha(), hashNoBanco);
        System.out.println(">>> O BCrypt reconhece essa senha contra o hash do banco? " + (senhaBateComHash ? "SIM" : "NÃO"));

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dados.login(), dados.senha())
            );
            System.out.println(">>> 2. Autenticou com sucesso via Auth  enticationManager!");

            Usuario usuario = (Usuario) auth.getPrincipal();
            String token = tokenService.gerarToken(usuario);
            System.out.println(">>> 3. Token gerado com sucesso!");

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (Exception e) {
            System.err.println(">>> ERRO NA AUTENTICAÇÃO: " + e.getMessage());
            // O stacktrace ajuda a ver se o erro vem do banco ou da lógica de segurança
            return ResponseEntity.status(403).build();
        }
    }

    public record UsuarioLoginDTO(String login, String senha) {}
    public record TokenDTO(String token, String tipo) {}
}