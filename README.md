# 🚀 FórumHub API

API REST desenvolvida em **Java + Spring Boot** como parte do **Challenge Back-End da Alura (Oracle Next Education)**.

O projeto simula o funcionamento de um **fórum de discussão**, permitindo que usuários autenticados criem, consultem, atualizem e excluam tópicos.

---

### 📚 Objetivo do Projeto

Este projeto foi desenvolvido para praticar:

- Construção de APIs REST

- Spring Boot

- Segurança com JWT

- Persistência com JPA

- Boas práticas de arquitetura

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

### 🚀 Como Rodar o Projeto

**Configure o Banco de Dados**
Certifique-se de que o schema `forumhub_schema` existe e as credenciais no arquivo `src/main/resources/application.properties` estão corretas:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub_schema
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

#### Compile o Projeto e teste
No terminal, na raiz do projeto, execute:

```Bash
mvn clean install -DskipTests
```

### Execute a Aplicação

```Bash
mvn spring-boot:run
```

A API estará disponível em: http://localhost:8080

_______

## 🛠️ Como Usar (Endpoints Principais)

Abaixo, os detalhes da implementação de **Exclusão Lógica** (campo `ativo`):

### **1. Listar Tópicos**
* **Método:** `GET /topicos`
* **Descrição:** Retorna apenas tópicos que possuem `ativo: true`.
* **Recursos:** Suporta paginação e ordenação (Ex: `
{
  "page": 0,
  "size": 10
}
`).

### **2. Buscar por ID**
* **Método:** `GET /topicos/{id}`
* **Comportamento:** Se o tópico existir no banco, mas estiver inativo (`ativo: false`), a API retornará `404 Not Found`.

### **3. Atualizar Tópico**
* **Método:** `PUT /topicos/{id}`
* **Regra:** Não é permitido atualizar tópicos que foram marcados como inativos. Caso o ID pertença a um tópico excluído logicamente, a API retornará `404`.

### **4. Remover Tópico (Soft Delete)**
* **Método:** `DELETE /topicos/{id}`
* **Comportamento:** O registro **não** é apagado fisicamente do banco de dados. O campo `ativo` é alterado para `false`, preservando o histórico e a integridade dos relacionamentos com autores e respostas.
______

### Contato
<div style="display: inline_block" align="left" > 
<a href="https://discord.gg/QXnhv9H7fC" target="_blank" alt="Sabrina Souza#5541" title="Sabrina Souza#5541"><img src="https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white" target="_blank"></a>
  <a href="https://mail.google.com/mail/u/0/#inbox?compose=CllgCJNrcmhcnjzCPDCbxXmtkDlWpFgcKKMPHktkGdltmNQvzLqFwwJDqCPpQHKbTKvQkgNwrbq" target="_blank" alt="lynxsabri@gmail.com" title="lynxsabri@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
  <a href="https://www.linkedin.com/in/sabrina-souza-6361a5148/" target="_blank" alt="sabrina-souza-6361a5148" title="sabrina-souza-6361a5148"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"/>
  </a>

### 📄 Licença

Este projeto está sob a licença MIT.