# Contacts App V2

## Descrição

Nesta versão do projeto, houve simplificação da lógica para integração com o front, utilizando apenas os endpoints de Person.

## Pré-requisitos

Antes de instalar e rodar o projeto, certifique-se de que você possui:

- **Java 17+** instalado ([Baixar aqui](https://adoptium.net/))
- **Maven 3.8+** instalado ([Baixar aqui](https://maven.apache.org/download.cgi))
- **Git** instalado para clonar o repositório ([Baixar aqui](https://git-scm.com/))
- **Mysql** instalado ([Baixar aqui](https://git-scm.com/)) e com o database 'persons_with_contacts' criado.

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

Abra o navegador e acesse [`http://localhost:8081/api/persons`](http://localhost:8081/api/persons) para ver a aplicação em execução (deve exibir a lista de Pessoas cadastradas).

P.S: Caso haja outra aplicação rodando localmente em sua máquina na porta 8081, será necessário alterar a porta do Contacts App através do arquivo application.properties, localizado no diretório src/main/resources.
Exemplos de portas:

- server.port=8081
- server.port=8082
- server.port=8083

## Endpoints da API

### Persons:

- **Criar Pessoa:** `POST /api/persons`
- **Obter Pessoa por ID:** `GET /api/persons/{id}`
- **Listar todas as Pessoas:** `GET /api/persons`
- **Atualizar Pessoa por ID:** `PATCH /api/persons/{id}`
- **Deletar Pessoa por ID:** `DELETE /api/persons/{id}`

📌 **Documentação completa:** [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html)

## Banco de dados

Essa versão da aplicação faz uso do **Mysql**, para persistência dos dados.

Configurar o banco com o usuário 'root' e senha 'ha159357', conforme definição no application.properties. Se preferir, poderá alterar no application.properties as configurações de usuário e senha.

## Testes Unitários

Neste projeto, foram incluídos testes unitários básicos para os controllers **Person** e **Contact**. Os testes abrangem as funcionalidades de inserção desses recursos, utilizando a abordagem de testes com `MockMvc`.

### Débito Técnico:

Devido a limitações de tempo, não foram implementados testes para todos os cenários possíveis, como:

- Testes dos demais endpoints
- Testes dos services
- Testes de integração mais complexos

Esses testes podem ser implementados em uma fase posterior do desenvolvimento, garantindo uma cobertura completa e robusta para o projeto.

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
