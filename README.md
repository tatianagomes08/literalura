# Literalura 📚

## Status do Projeto
✔️ Entregue

## Índice
- [Descrição do Projeto](#descrição-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Como Usar](#como-usar)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Desenvolvedora](#desenvolvedores)

## Descrição do Projeto
Literalura é um sistema console em Java para gerenciar um repositório de **livros** e **autores**, permitindo buscar livros no catálogo da [Gutendex](https://gutendex.com/) e salvar na literalura. Com isso é construído um catálogo de livros e autores.

## Funcionalidades
- Buscar livro pelo titulo -> Nesse caso é feita uma consulta na API da Gutendex e salvo no banco de dados o livro e o autor (evitando duplicidades)
- Listar livros registrados -> Aqui são listados todos os livros que já foram consultados na funcionalidade anterior
- Listar autores registrados -> Aqui são listados todos os autores de livros que já foram consultados na primeira funcionalidade
- Listar autores vivos em determinado ano -> Aqui é feito um pedido ao usuário para escolher o ano e é consultado no banco de dados por autores vivos naquele ano
- Listar livros em um determinado idioma -> Aqui é feita um pedido ao usuário para escolher o idioma e é consultado no banco de dados por todos os livros cujo idioma é o selecionado

## Como Usar

```bash
# Clone o repositório
git clone https://github.com/tatianagomes08/literalura.git

# Compile o projeto com Maven ou Gradle (ou via linha de comando):
mvn clean install

# Execute o jar
java -jar target/literalura.jar
```

Em seguida selecione 1 para buscar por livros e outras opções para fazer consultas sob o acervo de livros buscados.

## Tecnologias Utilizadas 🛠️

- **Java** — Linguagem principal utilizada no desenvolvimento.
- **Maven** — Gerenciador de dependências e build do projeto.
- **Spring Boot** — Para facilitar a configuração e execução da aplicação.
- **JPA / Hibernate** — Para mapeamento objeto-relacional.
- **Banco de Dados** — PostgreSQL.
- **API Gutendex** — Fonte de dados utilizada para buscar informações sobre livros e autores.
- **IDE** — IntelliJ IDEA.
- **Git & GitHub** — Controle de versão e hospedagem do código.

## Desenvolvedora
- Tatiana Gomes - [GitHub](https://github.com/tatianagomes08)
