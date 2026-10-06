# Tasker API

API REST para gerenciamento de **usuários e tarefas**, desenvolvida em Java com Spring Boot como parte de um projeto de teste técnico.

A aplicação permite cadastrar usuários, criar e gerenciar tarefas, definir responsáveis, controlar o status das tarefas e consultar tarefas por status ou usuário.

## Tecnologias

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Validation
- H2 Database
- Maven
- REST API
- Swagger / OpenAPI

## Arquitetura

O projeto segue uma arquitetura em camadas, separando as responsabilidades da aplicação:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
```

Também são utilizados DTOs para entrada e saída de dados, evitando a exposição direta das entidades JPA.

### Estrutura do projeto

```text
src/main/java/br/com/wbytesistemas/tasker_api/

├── controller/
│   ├── UserController.java
│   └── TaskController.java
│
├── service/
│   ├── UserService.java
│   └── TaskService.java
│
├── repository/
│   ├── UserRepository.java
│   └── TaskRepository.java
│
├── entity/
│   ├── UserEntity.java
│   ├── TaskEntity.java
│   └── TaskerStatus.java
│
├── dto/
│   ├── UserRequestDTO.java
│   ├── UserResponseDTO.java
│   ├── TaskRequestDTO.java
│   └── TaskResponseDTO.java
│
└── exception/
    ├── ResourceNotFoundException.java
    ├── EmailAlreadyExistsException.java
    └── GlobalExceptionHandler.java
```

## Funcionalidades

### Usuários

- Cadastro de usuários
- Validação de nome e e-mail
- Validação do formato do e-mail
- E-mail único
- Listagem de usuários
- Registro automático da data de criação

### Tarefas

- Cadastro de tarefas
- Título obrigatório
- Descrição opcional
- Controle de status
- Data de vencimento
- Associação da tarefa a um usuário responsável
- Listagem de tarefas
- Filtro por status
- Listagem de tarefas por usuário
- Atualização de tarefas
- Exclusão de tarefas
- Validação da data de vencimento

## Status das tarefas

As tarefas podem possuir um dos seguintes status:

```text
PENDENTE
EM_ANDAMENTO
CONCLUIDA
```

## Endpoints

### Usuários

#### Criar usuário

```http
POST /users
```

Exemplo de requisição:

```json
{
  "nome": "Willian Oliveira",
  "email": "willian@email.com"
}
```

#### Listar usuários

```http
GET /users
```

---

### Tarefas

#### Criar tarefa

```http
POST /tasks
```

Exemplo:

```json
{
  "titulo": "Task teste",
  "descricao": "Um teste de texto para testar a task",
  "status": "PENDENTE",
  "dataVencimento": "2026-10-10",
  "userId": 1
}
```

#### Listar tarefas

```http
GET /tasks
```

#### Filtrar tarefas por status

```http
GET /tasks?status=PENDENTE
```

Exemplos de status:

```text
PENDENTE
EM_ANDAMENTO
CONCLUIDA
```

#### Listar tarefas de um usuário

```http
GET /tasks/user/{userId}
```

Exemplo:

```http
GET /tasks/user/1
```

#### Buscar tarefa por ID

```http
GET /tasks/{id}
```

#### Atualizar tarefa

```http
PUT /tasks/{id}
```

Exemplo:

```json
{
  "titulo": "Task teste",
  "descricao": "Um teste de texto para testar a task",
  "status": "EM_ANDAMENTO",
  "dataVencimento": "2026-10-15",
  "userId": 1
}
```

#### Excluir tarefa

```http
DELETE /tasks/{id}
```

## Validações

A API possui validações para garantir a consistência dos dados.

### Usuário

- Nome obrigatório
- E-mail obrigatório
- E-mail deve possuir formato válido
- E-mail não pode ser duplicado

### Tarefa

- Título obrigatório
- Usuário responsável deve existir
- Status deve ser válido
- Data de vencimento não pode ser anterior à data atual

## Tratamento de exceções

A aplicação possui tratamento global de exceções utilizando `@RestControllerAdvice`.

São tratados, entre outros:

- Recursos não encontrados → `404 Not Found`
- E-mail já cadastrado → `400 Bad Request`
- Erros de validação → `400 Bad Request`

## Banco de dados

O projeto utiliza o **H2 Database** para facilitar a execução e os testes da aplicação.

As principais tabelas são:

```text
tb_users
    ├── id
    ├── nome
    ├── email
    └── created_at

tb_tasks
    ├── id
    ├── titulo
    ├── descricao
    ├── status
    ├── due_date
    └── user_id
```

A tabela `tb_tasks` possui um relacionamento `ManyToOne` com `tb_users`.

```text
User 1 ─────────── N Task
```

Uma tarefa possui um único usuário responsável, enquanto um usuário pode ser responsável por várias tarefas.

## Swagger / OpenAPI

A documentação da API está disponível através do Swagger UI.

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

O Swagger permite visualizar e testar os endpoints diretamente pelo navegador.

## Como executar

### Pré-requisitos

- Java 17 ou superior
- Maven
- Git

### Clonar o projeto

```bash
git clone https://github.com/Willian0liveira/tasker-api.git
```

Entrar no diretório:

```bash
cd tasker-api
```

### Executar com Maven

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Ou execute a classe principal da aplicação pela IDE.

A API estará disponível em:

```text
http://localhost:8080
```

## Testando a API

A API pode ser testada utilizando ferramentas como:

- Swagger UI
- Postman
- Insomnia
- Thunder Client

O Swagger é recomendado para uma primeira visualização dos endpoints e contratos da API.

## Códigos HTTP utilizados

| Código | Utilização |
|---|---|
| `200 OK` | Operação realizada com sucesso |
| `201 Created` | Recurso criado com sucesso |
| `204 No Content` | Operação realizada sem conteúdo de retorno |
| `400 Bad Request` | Dados inválidos ou erro de validação |
| `404 Not Found` | Recurso não encontrado |

## Objetivo do projeto

O projeto foi desenvolvido com foco na aplicação de conceitos de desenvolvimento de APIs REST utilizando Java e Spring Boot, incluindo:

- Arquitetura em camadas
- Injeção de dependências
- Spring Data JPA
- Relacionamentos entre entidades
- DTOs
- Bean Validation
- Tratamento global de exceções
- Persistência de dados
- HTTP Status Codes
- Documentação com OpenAPI/Swagger
- Boas práticas de organização e Clean Code

## Autor

**Willian Oliveira**

Desenvolvedor Mobile & Web

Portfólio:  
https://wbytesistemas.com.br

GitHub:  
https://github.com/Willian0liveira
