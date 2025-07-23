# GitHub User Manager API

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-blueviolet.svg)
![Gradle](https://img.shields.io/badge/Gradle-8.8-green.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.1-brightgreen.svg)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow.svg)

Uma API RESTful construída com Spring Boot e Kotlin para gerenciar perfis de usuários, populando a base inicial a partir da API pública do GitHub.

## 🚀 Funcionalidades Principais

* **Ingestão de Dados:** Busca e armazena usuários do GitHub (a princípio 30, mas pode ser alterado) na inicialização da aplicação.
* **Gerenciamento de Perfis:** Permite a criação de perfis (ex: "Developer", "Admin").
* **Vinculação de Perfis:** Associa os perfis criados aos usuários existentes.
* **Listagem:** Expõe um endpoint para listar todos os usuários e seus perfis vinculados.
* **Segurança:** Estrutura preparada para autenticação via Token JWT.
* **Banco de Dados:** Utiliza H2 (em memória) com versionamento de schema via Flyway.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
* **Build Tool:** [Gradle](https://gradle.org/)
* **Acesso a Dados:** [Spring Data JPA](https://spring.io/projects/spring-data-jpa) / Hibernate
* **Banco de Dados:** [H2 Database Engine](https://www.h2database.com/html/main.html) (para desenvolvimento)
* **Migrações:** [Flyway](https://flywaydb.org/)
* **Cliente HTTP:** [OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
* **Segurança:** [Spring Security](https://spring.io/projects/spring-security)

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
* Uma IDE de sua preferência (IntelliJ IDEA, VS Code com extensões, etc).

## 🏁 Como Executar o Projeto

Siga os passos abaixo para rodar o projeto localmente.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/github-manager.git](https://github.com/seu-usuario/github-manager.git)
    cd github-manager
    ```

2.  **Execute a aplicação com o Gradle Wrapper:**
    * No macOS/Linux:
        ```bash
        ./gradlew bootRun
        ```
    * No Windows:
        ```bash
        gradlew.bat bootRun
        ```

3.  A API estará disponível em `http://localhost:8080`.

4.  **Acessando o Console do Banco de Dados H2:**
    * Com a aplicação rodando, acesse `http://localhost:8080/h2-console` no seu navegador.
    * Use a URL `jdbc:h2:mem:githubdb` para se conectar e visualizar as tabelas.

## Endpoints da API

| Método | Path                           | Descrição                                 |
| :----- | :----------------------------- | :---------------------------------------- |
| `GET`  | `/users`                       | Lista todos os usuários e seus perfis.      |
| `POST` | `/profiles`                    | Cria um novo perfil.                      |
| `POST` | `/users/{userId}/profiles`     | Vincula um perfil existente a um usuário. |

## 📝 Convenção de Commits

Para manter o histórico do projeto organizado e legível, todos os commits devem seguir o padrão **Conventional Commits**. O formato é `<tipo>: <mensagem>` e as mensagens serão escritas em **inglês**.

* **feat:** Adição de uma nova funcionalidade (`feature`).
* **fix:** Correção de um bug (`bug fix`).
* **chore:** Tarefas de manutenção que não alteram código de produção (ex: atualizar dependências, configurar o build).
* **docs:** Alterações na documentação (ex: `README.md`).
* **style:** Alterações de formatação de código que não afetam a lógica (espaços, ponto e vírgula, etc.).
* **refactor:** Refatoração de código que não corrige um bug nem adiciona uma funcionalidade.
* **test:** Adição ou correção de testes.
* **build:** Alterações que afetam o sistema de build ou dependências externas.
* **ci:** Alterações nos arquivos e scripts de CI/CD.

**Exemplo:**
```bash
git commit -m "feat: Add endpoint to create user profiles"
```

## 📄 Licença

Este projeto está sob a Licença Apache 2.0. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.