# GitHub User Manager API

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-blueviolet.svg)
![Gradle](https://img.shields.io/badge/Gradle-8.8-green.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-brightgreen.svg)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow.svg)

API RESTful em Kotlin e Spring Boot para gerenciar usuários e perfis (roles), populando a base inicial a partir da API pública do GitHub.

> **Este projeto foi criado com [start.spring.io](https://start.spring.io/) usando as dependências:**  
> Spring Web, Spring Data JPA, H2 Database, Flyway Migration, Spring Security, OpenFeign, Validation

## 🚀 Funcionalidades

- Ingestão automática de usuários do GitHub na inicialização (via OpenFeign)
- Cadastro de usuários e perfis (roles)
- Associação de perfis a usuários
- Listagem de usuários com seus perfis vinculados
- Console H2 para visualização do banco em memória
- Estrutura pronta para autenticação JWT

## Tecnologias

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

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
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

3. Acesse a API em `http://localhost:8080`

4. **Acessando o Console do Banco de Dados H2:**
    - Com a aplicação rodando, acesse `http://localhost:8080/h2-console` no navegador.
    - Use a URL `jdbc:h2:mem:githubdb` para se conectar.

5. **Criando um usuário válido**
    - Com a aplicação rodando, faça uma requisição `POST` para `http://localhost:8080/auth/login` com o seguinte corpo:

    ``` {json}
    {
        "username":"seu_usuario",
        "password":"sua_senhar"
    }

    ``` 
    - Isso irá retornar um token JWT válido, que pode ser usado como Bearer Token nas próximas requisições

## Endpoints principais

| Método | Caminho                   | Descrição                                      |
|--------|---------------------------|------------------------------------------------|
| POST   | `/auth/login`             | Gera um token JWT  |
| GET    | [users](http://_vscodecontentref_/0)                  | Lista todos os usuários e seus perfis          |
| POST   | [users](http://_vscodecontentref_/1)                  | Cria um novo usuário                           |
| POST   | `/roles`                  | Cria um novo perfil (role)                     |
| POST   | `/users/{userId}/roles`   | Vincula um perfil a um usuário                 |

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