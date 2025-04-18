# blocoNotasAPI
Bloco de Notas API Simples (Em Memória) 📝

Um projeto iniciante em **Java com Spring Boot** para criar uma API REST básica de um bloco de notas.

Este projeto demonstra os fundamentos de criação de endpoints (URLs) para:

*   Adicionar novas notas
*   Listar todas as notas existentes
*   Buscar uma nota específica pelo seu ID
*   Deletar uma nota pelo seu ID

**Importante:** As notas são armazenadas **apenas na memória** da aplicação. Isso significa que **todas as notas serão perdidas** quando a aplicação for parada ou reiniciada.

## Funcionalidades

*   `POST /api/notes` - Cria uma nova nota. Envie o texto da nota no corpo da requisição (tipo `text/plain`).
*   `GET /api/notes` - Lista todas as notas criadas.
*   `GET /api/notes/{id}` - Busca e retorna uma nota específica pelo ID fornecido.
*   `DELETE /api/notes/{id}` - Deleta a nota com o ID fornecido.

## Como Rodar Localmente

**Pré-requisitos:**

*   Java JDK (Versão 11 ou superior recomendada)
*   Maven

**Passos:**

1.  Clone este repositório:
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO_AQUI]
    ```
2.  Navegue até a pasta do projeto:
    ```bash
    cd bloco-notas-api
    ```
3.  Execute a aplicação usando o Maven:
    ```bash
    mvn spring-boot:run
    ```
    (Ou execute a classe principal `BlocoNotasApiApplication.java` diretamente da sua IDE).

4.  A API estará disponível em `http://localhost:8080`.

## Como Usar a API (Exemplos com `curl`)

*   **Criar Nota:**
    ```bash
    curl -X POST -H "Content-Type: text/plain" -d "Lembrar de estudar Spring!" http://localhost:8080/api/notes
    ```

*   **Listar Notas:**
    ```bash
    curl http://localhost:8080/api/notes
    ```

*   **Buscar Nota por ID (Ex: ID 1):**
    ```bash
    curl http://localhost:8080/api/notes/1
    ```

*   **Deletar Nota por ID (Ex: ID 1):**
    ```bash
    curl -X DELETE http://localhost:8080/api/notes/1
    ```

## Tecnologias Utilizadas

*   Java
*   Spring Boot
*   Spring Web
*   Maven
