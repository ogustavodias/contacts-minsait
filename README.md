# Contacts App

## Descrição

O Contacts App é uma aplicação para gerenciar pessoas e seus respectivos contatos. Ele permite adicionar, editar, remover e visualizar pessoas e seus contatos de maneira fácil e eficiente.

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

Abra o navegador e acesse `http://localhost:8081/api/users` para ver a aplicação em execução (deve exibir a lista de usuários cadastrados).

P.S: Caso haja outra aplicação rodando localmente em sua máquina na porta 8081, será necessário alterar a porta do Contacts App através do arquivo application.properties, localizado no diretório src/main/resources.
Exemplos de portas:

- server.port=8081
- server.port=8082
- server.port=8083

## Endpoints da API

- **Listar pessoas:** `GET /api/person`
- **Criar pessoa:** `POST /api/person`
- **Obter pessoa por ID:** `GET /api/person/{id}`
- **Atualizar usuário:** `PUT /api/person/{id}`
- **Excluir usuário:** `DELETE /api/person/{id}`

- **Listar contatos:** `GET /api/person`
- **Criar contatos:** `POST /api/person`
- **Obter contato por ID:** `GET /api/person/{id}`
- **Atualizar contato:** `PUT /api/person/{id}`
- **Excluir contato:** `DELETE /api/person/{id}`

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
