# Contacts App

## Descrição

O Contacts App é uma aplicação para gerenciar Pessoas e seus respectivos Contatos. Ele permite adicionar, editar, remover e visualizar Pessoas e seus Contatos de maneira fácil e eficiente.

## Pré-requisitos

Antes de instalar e rodar o projeto, certifique-se de que você possui:

- **Java 17+** instalado ([Baixar aqui](https://adoptium.net/))
- **Maven 3.8+** instalado ([Baixar aqui](https://maven.apache.org/download.cgi))
- **Git** instalado para clonar o repositório ([Baixar aqui](https://git-scm.com/))

## Instalação

Para instalar e rodar o projeto localmente, siga os passos abaixo:

1. Clone o repositório:

   ```bash
   git clone https://github.com/ogustavodias/contacts-minsait.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd contacts-minsait
   ```

3. Instale as dependências:
   ```bash
   mvn clean install
   ```

## Uso

Para iniciar a aplicação, execute o comando:

```bash
mvnw spring-boot:run
```

Abra o navegador e acesse [`http://localhost:8081/api/users`](http://localhost:8081/api/users) para ver a aplicação em execução (deve exibir a lista de Pessoas cadastradas).

P.S: Caso haja outra aplicação rodando localmente em sua máquina na porta 8081, será necessário alterar a porta do Contacts App através do arquivo application.properties, localizado no diretório src/main/resources.
Exemplos de portas:

- server.port=8081
- server.port=8082
- server.port=8083

## Endpoints da API

- **Criar Pessoa:** `POST /api/persons`
- **Obter Pessoa por ID:** `GET /api/persons/{id}`
- **Obter Pessoa por ID para mala direta:** `GET /api/persons/directmail/{id}`
- **Listar todas as Pessoas:** `GET /api/persons`
- **Atualizar Pessoa por ID:** `PATCH /api/persons/{id}`
- **Deletar Pessoa por ID:** `DELETE /api/persons/{id}`

- **Adicionar um novo Contato a uma Pessoa:** `POST /api/contacts`
- **Obter Contato por ID:** `GET /api/contacts/{id}`
- **Listar todos os Contatos de uma Pessoa:** `GET /api/contacts/person/{personId}`
- **Atualizar Contato por ID :** `PATCH /api/contacts/{id}`
- **Deletar Contato por ID:** `DELETE /api/contacts/{id}`

📌 **Documentação completa:** [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html)

## Banco de dados

Essa aplicação faz uso do **H2 database**, para persistência em memória.

Acesse o console do banco via navegador:  
📌 [`http://localhost:8081/h2-console/`](http://localhost:8081/h2-console/)

Use as credenciais:

- **Driver Class:** h2.Driver
- **JDBC URL:** jdbc:h2:mem:app-contacts
- **User Name:** sa
- **Password:**

P.S: Password em branco.

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
