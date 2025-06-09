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
- Mensagens de validação e erro com suporte à internacionalização em **três idiomas**: 🇧🇷 Português, 🇺🇸 Inglês e 🇪🇸 Espanhol.

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
git clone https://github.com/JhonatanSampaioF/apo-ia.git
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

---

## 🌐 Internacionalização

O Apo.IA oferece suporte a mensagens de erro e validação em três idiomas:

- 🇧🇷 **Português** (padrão)
- 🇺🇸 **Inglês**
- 🇪🇸 **Espanhol**

### 📥 Como funciona:

- O idioma é detectado automaticamente via header `Accept-Language` da requisição HTTP.
- Mensagens de validação, como campos obrigatórios ou mal formatados, são retornadas de forma amigável para o idioma do usuário.

### 📤 Exemplo de requisição com `Accept-Language: en`

```http
POST /usuario
Content-Type: application/json
Accept-Language: en
```

```body
{
  "email": "user@email.com",
  "senha": "123456"
}
```

```response
{
  "status": 400,
  "error": "Bad Request",
  "errors": [
    {
      "field": "nome",
      "message": "Name is required"
    }
  ]
}
```
> O mesmo exemplo retorna mensagens traduzidas automaticamente se o idioma for pt-BR ou es.

## 🛠️ Arquivos utilizados:
- messages.properties (Português)
- messages_en.properties (Inglês)
- messages_es.properties (Espanhol)

Esses arquivos se encontram em src/main/resources e são utilizados para internacionalização com MessageSource do Spring.

## 🔗 Links Úteis

- 🚀 [Deploy da aplicação](https://apo-ia.azurewebsites.net)
- 🎥 [Pitch da Solução](https://youtu.be/ly719d8vVvM?si=7uFpbkyNURryVkYD)
- 🎬 [Demonstração completa](https://youtu.be/iQLERSbk3_A)

## 👨‍💻 Equipe de Desenvolvimento

| Nome                 | Função               | RM        |
|----------------------|----------------------|-----------|
| Jhonatan Sampaio     | Backend Developer    | RM553791  |
| Gustavo Vieira Bargas| Database Analyst     | RM553471  |
| Vivian Sy Ting Wu    | Frontend Developer   | RM553169  |

> 📚 Este projeto foi desenvolvido como parte da disciplina de Java do projeto Global Solution na FIAP — 4º semestre, com foco em soluções tecnológicas de impacto social.
