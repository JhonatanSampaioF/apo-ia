# 🧠 Apo.IA — Sistema de Apoio a Centros Humanitários

O **Apo.IA** é um sistema web completo voltado para gestão de pessoas em situações de vulnerabilidade, como desastres naturais, guerras ou situações de emergência social. Com foco em centros humanitários, o sistema permite o cadastro e acompanhamento de **abrigados**, **voluntários**, **doenças**, **habilidades** e **locais de acolhimento**, utilizando **Spring Boot**, **MongoDB**, **RabbitMQ** e integração com **IA Generativa (via Ollama)**.

---

## 🧩 Funcionalidades Principais

- Cadastro de **abrigados** com mapeamento de doenças e voluntariado.
- Gerenciamento de **voluntários** com habilidades e alocação por local.
- CRUD de **doenças**, **habilidades** e **grupos de habilidades**.
- Atribuição de doenças a abrigados e habilidades a voluntários.
- Integração com IA Generativa usando **Spring AI + Ollama**.
- Execução assíncrona com **RabbitMQ** (inicialização e filas).
- Camada de autenticação e segurança com **JWT + Spring Security**.
- Interface web (MVC) com páginas para login, cadastro e gerenciamento das entidades.

---

## 🚀 Tecnologias Utilizadas

| Camada        | Tecnologias                                                                |
|---------------|----------------------------------------------------------------------------|
| Backend       | Spring Boot 3.5, Spring Data MongoDB, Spring Security, Spring AI, RabbitMQ |
| Banco de Dados| MongoDB                                                                    |
| Filas         | RabbitMQ                                                                   |
| Testes        | JUnit 5, Mockito, Awaitility, TestContainers (opcional)                    |
| Build Tool    | Maven                                                                      |
| Outras        | Jakarta Validation, Lombok, Swagger, Thymeleaf (MVC)                                      |

---

## 🛠️ Como Rodar o Projeto Localmente

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/apo-ia.git
cd apo-ia
```

### 2. Subir MongoDB e RabbitMQ com Docker

```bash
docker compose up -d
```

### 3. Rodar o projeto

```bash
./mvnw spring-boot:run
```

A aplicação iniciará em http://localhost:8080.

## 🖥️ Interface Web (Spring MVC)

O sistema inclui uma interface web baseada em Spring MVC, acessível via navegador.  
Após iniciar a aplicação, acesse:

🔗 [http://localhost:8080](http://localhost:8080)

A partir da página inicial, é possível realizar login, cadastrar dados e gerenciar todas as entidades.

---

## 🔐 Acesso ao Sistema

Ao iniciar o projeto pela primeira vez, um usuário **administrador padrão** é criado automaticamente.

> **Credenciais para acesso inicial:**
> - **Email:** `admin@email.com`
> - **Senha:** `admin`



## 🧪 Executar Testes

### 1. Testes Unitários + Integração

```bash
./mvnw test
```

### 2. Para rodar apenas testes de fila:

```bash
mvn -Dtest=InitializerQueueIntegrationTest test
```

## 🧠 Integração com IA
O sistema utiliza Spring AI com Ollama para análise de dados dos abrigados, ferimentos, doenças e perfil dos voluntários para sugerir alocações ideais automaticamente.

Para rodar o Ollama localmente:

```bash
ollama run llama3
```

## 🔗 Links Úteis

- 🚀 [Deploy da aplicação (placeholder)](https://)
- 🎥 [Pitch da Solução](https://)
- 🎬 [Demonstração completa](https://youtu.be/P6_0U3Kl9EU)

## 👨‍💻 Equipe de Desenvolvimento

| Nome                 | Função               | RM        |
|----------------------|----------------------|-----------|
| Jhonatan Sampaio     | Backend Developer    | RM553791  |
| Gustavo Vieira Bargas| Database Analyst     | RM553471  |
| Vivian Sy Ting Wu    | Frontend Developer   | RM553169  |

> 📚 Este projeto foi desenvolvido como parte da disciplina de Java do projeto Global Solution na FIAP — 4º semestre, com foco em soluções tecnológicas de impacto social.
