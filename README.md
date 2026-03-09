# 🚀 FórumHub API

API REST desenvolvida em **Java + Spring Boot** como parte do **Challenge Back-End da Alura (Oracle Next Education)**.

O projeto simula o funcionamento de um **fórum de discussão**, permitindo que usuários autenticados criem, consultem, atualizem e excluam tópicos.

---

# 📌 Funcionalidades

A API oferece os seguintes recursos principais:

- 📝 **Criar um novo tópico**
- 📄 **Listar todos os tópicos**
- 🔎 **Buscar um tópico por ID**
- ✏️ **Atualizar um tópico**
- 🗑️ **Excluir um tópico**
- 🔐 **Autenticação de usuários com JWT**
- 👤 **Controle de acesso baseado em usuários**

---

# 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| Java | Linguagem principal |
| Spring Boot | Framework para construção da API |
| Spring Security | Autenticação e autorização |
| JWT | Segurança baseada em token |
| JPA / Hibernate | ORM para persistência |
| PostgreSQL | Banco de dados relacional |
| Flyway | Versionamento do banco de dados |
| Maven | Gerenciamento de dependências |

---

# 🏗️ Arquitetura do Projeto

A aplicação segue uma arquitetura baseada em camadas:

```json
src
│
├── controller
│ └── endpoints REST
│
├── domain
│ └── entidades e regras de negócio
│
├── dto
│ └── objetos de transferência de dados
│
├── repository
│ └── acesso ao banco de dados
│
├── service
│ └── lógica de negócio
│
└── infra
└── segurança, exceções e configurações

```
---

# 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação.

Fluxo:

1. Usuário faz login
2. API retorna um **Token JWT**
3. Token é enviado nas requisições protegidas

Exemplo de header:
Authorization: Bearer seu_token_aqui


---

# 📡 Endpoints Principais

| Método | Endpoint | Descrição |
|------|------|------|
| POST | `/login` | Autenticação do usuário |
| GET | `/topicos` | Lista todos os tópicos |
| GET | `/topicos/{id}` | Busca um tópico |
| POST | `/topicos` | Cria um novo tópico |
| PUT | `/topicos/{id}` | Atualiza um tópico |
| DELETE | `/topicos/{id}` | Remove um tópico |

---

# ⚙️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/SabrinaSouzaDev/alura-One-desafio-forum-hub.git
```

Entrar na pasta
```bash
cd alura-One-desafio-forum-hub
```

mvn spring-boot:run

### 🧪 Testando a API

Você pode testar os endpoints utilizando:

### 📚 Objetivo do Projeto

Este projeto foi desenvolvido para praticar:

- Construção de APIs REST

- Spring Boot

- Segurança com JWT

- Persistência com JPA

- Boas práticas de arquitetura


### 👩‍💻 Autora

**Sabrina Souza**

GitHub
https://github.com/SabrinaSouzaDev

### 📄 Licença

Este projeto está sob a licença MIT.