# Desafio Java jr - NT Consult
Este é o repositório com a solução proposta para o desafio-java-jr da NT Consult. Trata-se de API REST simples usando Spring Boot, onde usuários podem gerenciar
informações básicas de livros.

## Arquitetura e decisões técnicas
### Stack utilizada:
- Java 21
- Spring boot 4.1.2 (Spring Data, Spring Web MVC)
- H2 Database (Banco de Dados em Memória)
- Maven 3.10.0

### Arquitetura e organização dos diretórios
Foi utilizada uma arquitetura em camada (Controller, Services, Repositories, Domain model):

```
src/main/java/br/com/san/desafiojavajr/
├── web/controller/         # Controladores, interceptadores REST
├── web/dto/                # DTOs
├── service/                # Serviços
├── exceptions/             # Exceções de negócio
├── exceptions/handler/     # Handler, interceptador de exceções
├── repository/             # Camada de repositório / persistência de dados
└── domain/model            # Entidades do dominio
```

## Pré-requisitos e instalação

### Requisitos do sistema:
É necessário ter instalado:
- Java 21 / JDK 21
- Maven Maven 3.10.0 (ou pode usar o wrapper `./mvnw`)
- Git

#### Instalação e execução

1. Clone o repositório e acesse o diretório do projeto:
```bash
git@github.com:Denis-san/desafio-java-jr-nt-consult.git
cd desafio-java-jr-nt-consult
```

2. Realize o build da app:
```bash
./mvnw clean package
```

3. Execute a aplicação:
```bash
java -jar target/*.jar --spring.profiles.active=dev
```
A aplicação estará rodando em `http://localhost:8080`.

#### Como executar os Testes

Para executar todos os testes unitários, utilize o perfil de testes e o comando abaixo:
```bash
./mvnw test -Dspring.profiles.active=test
```

## Documentação da API - rotas

Tabela de rotas disponíveis da API:

### Endpoints

| Endpoint | Descrição | Parametros | Retorno |
| --- | --- | --- | --- |
| `[GET] /livros/{id}` | Retorna os dados de um Livro baseado no seu `id` | `id`* (integer) | **200**: Os dados do Livro, **404**: Livro não encontrado |
| `[PUT] /livros/{id}` | Atualiza os dados de um Livro | `id`* (integer), Body: `UpdateLivroDTO` | **200**: Os novos dados do Livro atualizado |
| `[DELETE] /livros/{id}` | Deletar Livro da base de dados, baseado no seu `id` | `id`* (integer) | **204**: Livro deletado com sucesso |
| `[GET] /livros` | Lista todos os Livros existentens na base de dados | N/A | **200**: A lista de Livros disponíveis na base de dados |
| `[POST] /livros` | Cria um registro de um livro na base de dados | Body: `CreateLivroDTO` | **201**: Registro criado com sucesso |

---

### Respostas de erros

| Status Code | Descrição |
| --- | --- |
| **400** | Requisição mal formada / Dados inválidos / Campos ausentes |
| **500** | Erro interno desconhecido |


## Algumas decisões técnicas e comentários
- **Maven** É um gerenciador um pouco mais rígido, porém, com uma boa legibilidade, é de fácil entendimento e recomendado em projetos relativamente simples.
- **Java 21** Essa versão foi escolhida porque ainda está em suporte LTS e com isso há mais garantia de compatibilidade com a versão do framework e bibliotecas utilizadas (Spring).
- **Spring Boot** - Escolhido por facilitar a criação do projeto, fornecer autoconfiguração, por possuir comunidade extensa, pela sua robustez e por ser um dos padrões de mercado.
- **Spring Web** - Escolhido por ser o projeto/framework padrão para construções de Aplicações web (REST API) do ecossistema Spring.
- **Spring Data** - Escolhido por ser o projeto/framework padrão para lidar com persistência de dados no ecossistema Spring.
- **H2 Database** - Por se tratar de um projeto desafio, na descrição do mesmo, indica-se utilizar um banco de dados em memória.O H2 é ideal para prototipagem e escolhido pela sua velocidade e simplicidade.


## Sugestões de melhorias:
- **Paginação:** Dada natureza simples do projeto, o endpoint de listagem dos Livros retornará **todos os livros**. Em produção o ideal é paginar esses dados para impedir abusos e até mesmo esgotamento de recursos computacionais.
- **Teste de validações cobrindo mais cenários e Testes de integração:** Como solicitado os testes são básicos, não cobrem a maioria de cenários de execução. Em produção o ideal é estipular mais cenários e criar testes destinados à esses.
- **Migrations:** Ao se utilizar um banco de dados relacional real, inclusive em produção o ideal é criar `migrations`, para versionar as mudanças no schema do banco e garantir integridade dos dados quando houver mudanças no schema.
- **Versionar a API:** Utilizar a estratégia de versionamento de API é tido como uma boa pratica, isso porque podemos garantir que a API evolua sem quebrar o client/consumidor que utiliza versões anteriores.

---

## Sobre o desafio:

### Requisitos

Cadastro de Livros: Crie uma entidade chamada Livro com os seguintes campos:

- id (Long) - identificador único do livro
- titulo (String) - título do livro
- autor (String) - autor do livro
- anoPublicacao (Integer) - ano de publicação
- A API deve permitir o cadastro de livros.

- Consulta de Livros: A API deve permitir a consulta dos livros cadastrados. Implemente uma rota que retorne a lista de todos os livros cadastrados
- Atualização de Livros: A API deve permitir a atualização dos dados de um livro, com base no id.
- Exclusão de Livros: A API deve permitir a exclusão de um livro, usando o id.

#### Critérios de avaliação

- Estrutura da API: Implemente corretamente as rotas e os métodos HTTP necessários (GET, POST, PUT, DELETE).
- Persistência de Dados: Use um banco de dados simples, como H2 (ou banco de dados em memória), para persistir os livros.
- Código Limpo e Organizado: Demonstre boa organização do código e clareza nas implementações.
- Testes: Crie testes básicos para garantir que as rotas da API funcionam corretamente.
- Testes Unitários: Adicione testes básicos usando JUnit ou outro framework de sua escolha para testar as rotas da API.
- Decisões Técnicas: Justifique a escolha das tecnologias utilizadas (Spring Boot, banco de dados, etc.).
- Documentação Simples: Documente brevemente as rotas da API, descrevendo o que cada uma faz.

---

# Informações de contato
Denilson Santana da Conceição | 
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/denilson-santana-conceicao) | 
[![Email](https://img.shields.io/badge/Email-D14836?style=flat&logo=gmail&logoColor=white)](mailto:denilson.nt.nc@gmail.com)
