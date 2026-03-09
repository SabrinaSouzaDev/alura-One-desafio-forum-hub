-- Criação do ENUM para categoria de cursos (opcional, mas seguro)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'categoria_enum') THEN
        CREATE TYPE categoria_enum AS ENUM (
            'mobile',
            'programacao',
            'front-end',
            'devops',
            'ux & design',
            'data science',
            'inovacao & gestao',
            'inteligencia artificial'
        );
    END IF;
END$$;

-- Tabela autores
CREATE TABLE autores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Tabela cursos
CREATE TABLE cursos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria categoria_enum NOT NULL
);

-- Tabela topicos
CREATE TABLE topicos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL REFERENCES autores(id),
    curso_id BIGINT NOT NULL REFERENCES cursos(id)
);

-- Tabela respostas
CREATE TABLE respostas (
    id BIGSERIAL PRIMARY KEY,
    solucao TEXT,
    topico_id BIGINT NOT NULL REFERENCES topicos(id),
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    autor_id BIGINT NOT NULL REFERENCES autores(id)
);

-- Tabela usuarios
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);