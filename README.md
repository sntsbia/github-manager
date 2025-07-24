# GitHub User Manager API

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-blueviolet.svg)
![Gradle](https://img.shields.io/badge/Gradle-8.8-green.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-brightgreen.svg)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow.svg)

Uma API RESTful construída com Spring Boot e Kotlin para gerenciar usuários e perfis (roles), populando a base inicial a partir da API pública do GitHub.

> **Este projeto foi criado com [start.spring.io](https://start.spring.io/) utilizando as dependências:**  
> Spring Web, Spring Data JPA, H2 Database, Flyway Migration, Spring Security, OpenFeign, Validation.

## 🚀 Funcionalidades

- **Ingestão de Dados:** Busca e armazena usuários do GitHub (a princípio 30, mas pode ser alterado) na inicialização da aplicação.  
- **Gerenciamento de Perfis:** Permite a criação de perfis (roles) como "Developer", "Admin" etc.
- **Vinculação de Perfis:** Associa perfis criados aos usuários existentes.
- **Listagem:** Endpoint para listar todos os usuários e seus perfis vinculados.
- **Segurança:** Estrutura preparada para autenticação via Token JWT (ainda não implementada).
- **Banco de Dados:** Utiliza H2 (em memória) com versionamento de schema via Flyway.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
- **Build Tool:** [Gradle](https://gradle.org/)
- **Acesso a Dados:** [Spring Data JPA](https://spring.io/projects/spring-data-jpa) / Hibernate
- **Banco de Dados:** [H2 Database Engine](https://www.h2database.com/html/main.html)
- **Migrações:** [Flyway](https://flywaydb.org/)
- **Cliente HTTP:** [OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- **Segurança:** [Spring Security](https://spring.io/projects/spring-security)
- **Validação:** [Jakarta Validation](https://jakarta.ee/specifications/bean-validation/)

## ⚙️ Pré-requisitos

- [JDK 21](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
- Uma IDE de sua preferência (IntelliJ IDEA, VS Code, etc).

## 🏁 Como Executar o Projeto

1. **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/github-manager.git
    cd github-manager
    ```

2. **Execute a aplicação com o Gradle Wrapper:**
    - No macOS/Linux:
        ```bash
        ./gradlew bootRun
        ```
    - No Windows:
        ```bash
        gradlew.bat bootRun
        ```

3. A API estará disponível em `http://localhost:8080`.

4. **Acessando o Console do Banco de Dados H2:**
    - Com a aplicação rodando, acesse `http://localhost:8080/h2-console` no navegador.
    - Use a URL `jdbc:h2:mem:githubdb` para se conectar.

## Endpoints da API

| Método | Path                     | Descrição                                      |
|--------|--------------------------|------------------------------------------------|
| GET    | [users](http://_vscodecontentref_/0)                 | Lista todos os usuários e seus perfis.         |
| POST   | `/roles`                 | Cria um novo perfil (role).                    |
| POST   | [users](http://_vscodecontentref_/1)                 | Cria um novo usuário.                          |
| POST   | `/users/{userId}/roles`  | Vincula um perfil existente a um usuário.      |

## ⚠️ Pontos pendentes

* Implementar autenticação JWT.

* Adicionar testes para listagem de usuários com seus perfis.

* Implementar o serviço DataInitializerService para ingestão automática de usuários do GitHub.

## 📝 Convenção de Commits

Todos os commits devem seguir o padrão **Conventional Commits**: `<tipo>: <mensagem>` (em inglês).

- **feat:** Nova funcionalidade
- **fix:** Correção de bug
- **chore:** Manutenção (ex: dependências)
- **docs:** Documentação
- **style:** Formatação
- **refactor:** Refatoração
- **test:** Testes
- **build:** Build/dependências
- **ci:** Integração contínua

**Exemplo:**
```bash
git commit -m "feat: Add endpoint to create user profiles"
```

## 📄 Licença

Este projeto está sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.