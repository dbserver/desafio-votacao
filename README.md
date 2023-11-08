# Desafio Votação - Manual

## Sobre

Esta aplicação está usando a versão 17 do Java com a versão 3.1.5 do Spring. Esta aplicação, conforme o solicitado no
desafio, fornece um sistema onde você pode cadastrar pautas, sessões, associados e registrar votos referente às
pautas.

## Testes

Esta aplicação exige ao menos 90% de coverage de testes nas linhas de código. Você pode verificar o coverage por meio de sua IDE
executando os testes com coverage, ou utilizando outra ferramenta de sua preferência.

## Inicializando a aplicação

Esta aplicação tem duas formas de ser inicializada. A principal e mais recomendada é fazendo o uso do Docker.
Para iniciar desta forma, certifique-se que o seu Docker está inicializado e digite o seguinte na linha de comando:

```
  docker-compose up --build
```

Ao digitar este comando, a aplicação passará pelo processo de build, onde ocorrerão os testes e então estará disponível
juntamente ao banco de dados e ao pgadmin caso seja necessário manipular algum dado por fora da própria aplicação. Lembrando que
o acesso ao pgadmin deve ser permitido somente aos desenvolvedores.

Caso você não queira inicializar a aplicação pelo Docker, é necessário fornecer uma conexão ao banco de dados compatível
onde os dados da aplicação possam ser persistidos. Estes dados podem ser configurados no arquivo "application.properties".
Após realizar a configuração das variáveis você pode buildar e inicializar a aplicação por meio da sua IDE, caso 
tenha a possibilidade, ou também por meio da linha de comando realizando manualmente o build da aplicação e então o run da mesma.

## Acessar o PGAdmin

Caso o desenvolvedor tenha a necessidade de consultar ou manipular os dados do banco sem utilizar a interface, o ambiente
construído pelo docker dispõe também a aplicação PGAdmin para realizar essas consultas. Este ambiente pode ser acessado
pelo endereço: http://localhost:5050/ e fazendo o login conforme o email e senha definidos nas variáveis de ambiente de seu
docker-compose.


## Manual da API - 1.1.0

Você pode realizar suas requisições por meio de seu API Client como Insomnia ou Postman, ou pode utilizar a interface
swagger disponível no endereço: http://localhost:8080/swagger-ui/index.html

### Associados
#### Cadastrar um associado
  Endpoint para realizar o cadastro de um associado no sistema
```http
  POST /session/create
```
##### Dados de entrada

| Campo  |   Tipo   | Obrigatório | Local de entrada |             Descrição              |
|:------:|:--------:|:------------:|:----------------:|:----------------------------------:|
| `nome` | `string` |     Sim      |       Body       | Nome do associado a ser cadastrado |
| `cpf`  | `string` |     Sim      |       Body       | CPF do associado a ser cadastrado  |

##### Dados de retorno

| Campo |   Tipo    | Obrigatório |                  Descrição                   |
|:-----:|:---------:|:-----------:|:--------------------------------------------:|
| `id`  | `integer` |     Sim     | Código identificador gerado para o associado |
| `cpf` | `string`  |     Sim     |         CPF do associado cadastrado          |

### Pautas
#### Cadastrar uma pauta
  Endpoint para realizar o cadastro de uma pauta no sistema
```http
  POST /ruling/create
```
##### Dados de entrada

|     Campo     |   Tipo   | Obrigatório | Local de entrada |              Descrição              |
|:-------------:|:--------:|:------------:|:----------------:|:-----------------------------------:|
|    `title`    | `string` |     Sim      |       Body       |  Título da pauta a ser cadastrada   |
| `description` | `string` |     Sim      |       Body       | Descrição da pauta a ser cadastrada |

##### Dados de retorno

|     Campo     |   Tipo    | Obrigatório |              Descrição               |
|:-------------:|:---------:|:-----------:|:------------------------------------:|
|  `rulingId`   | `integer` |     Sim     | Código identificador da pauta criada |
|    `title`    | `string`  |     Sim     |        Título da pauta criada        |
| `description` | `string`  |     Sim     |      Descrição da pauta criada       |

#### Contagem de votos
  Endpoint para realizar a contagem de votos da sessão mais recente desta pauta.
```http
  POST /ruling/countVotes/{rulingId}
```
##### Dados de entrada

|   Campo    |   Tipo    | Obrigatório | Local de entrada |           Descrição           |
|:----------:|:---------:|:------------:|:----------------:|:-----------------------------:|
| `rulingId` | `integer` |     Sim      |       Path       | Código identificador da pauta |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |                Descrição                |
|:--------------:|:---------:|:-----------:|:---------------------------------------:|
|    `title`     | `string`  |     Sim     |             Título da pauta             |
| `description`  | `string`  |     Sim     |           Descrição da pauta            |
|    `result`    | `string`  |     Sim     |          Resultado da votação           |
| `inFavorVotes` | `integer` |     Sim     |       Quantidade de votos a favor       |
| `againstVotes` | `integer` |     Sim     |       Quantidade de votos contra        |
| `creationDate` | `string`  |     Sim     |        Data de criação da pauta         |
| `sessionDate`  | `string`  |     Sim     | Data de criação da sessão contabilizada |
|  `countDate`   | `string`  |     Sim     |       Data de contagem dos votos        |

#### Buscar todas as pautas
Endpoint para buscar todas as pautas cadastradas no sistema
```http
  GET /ruling/list
```
##### Dados de entrada

| Campo | Tipo  | Obrigatório | Local de entrada | Descrição |
|:-----:|:-----:|:-----------:|:----------------:|:---------:|
| `N/A` | `N/A` |     N/A     |       N/A        |    N/A    |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |           Descrição           |
|:--------------:|:---------:|:-----------:|:-----------------------------:|
|      `id`      | `integer` |     Sim     | Código identificador da pauta |
|    `title`     | `string`  |     Sim     |        Título da pauta        |
| `description`  | `string`  |     Sim     |      Descrição da pauta       |
|    `result`    | `string`  |     Sim     |     Resultado da votação      |
| `creationDate` | `string`  |     Sim     |   Data de criação da pauta    |
|  `resultDate`  | `string`  |     Não     |   Data da contagem de votos   |

#### Buscar pauta por código identificador
Endpoint para buscar uma pauta por meio de seu código identificador
```http
  GET /ruling/list/{id}
```
##### Dados de entrada

| Campo |   Tipo    | Obrigatório | Local de entrada |           Descrição           |
|:-----:|:---------:|:-----------:|:----------------:|:-----------------------------:|
| `id`  | `integer` |     Sim     |       Path       | Código identificador da pauta |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |           Descrição           |
|:--------------:|:---------:|:-----------:|:-----------------------------:|
|      `id`      | `integer` |     Sim     | Código identificador da pauta |
|    `title`     | `string`  |     Sim     |        Título da pauta        |
| `description`  | `string`  |     Sim     |      Descrição da pauta       |
|    `result`    | `string`  |     Sim     |     Resultado da votação      |
| `creationDate` | `string`  |     Sim     |   Data de criação da pauta    |
|  `resultDate`  | `string`  |     Não     |   Data da contagem de votos   |

### Sessões
#### Cadastrar uma sessão
Endpoint para realizar o cadastro de uma sessão no sistema
```http
  POST /session/create
```
##### Dados de entrada

|   Campo    |   Tipo    | Obrigatório | Local de entrada |                                                    Descrição                                                     |
|:----------:|:---------:|:-----------:|:----------------:|:----------------------------------------------------------------------------------------------------------------:|
| `rulingId` | `integer` |     Sim     |       Body       |                             Código identificador da pauta na qual a sessão irá votar                             |
| `duration` | `integer` |     Não     |       Body       | Duração da sessão em segundos. Caso não esteja presente este dado no envio,<br/> será adotado o valor de 60 segundos. |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |               Descrição               |
|:--------------:|:---------:|:-----------:|:-------------------------------------:|
|  `sessionId`   | `integer` |     Sim     | Código identificador da sessão criada |
| `creationDate` | `string`  |     Sim     |       Data de criação da sessão       |
|   `duration`   | `integer` |     Sim     |     Duração da sessão em segundos     |

#### Listar todas as sessões
Endpoint para realizar a busca de todas as sessões cadastradas no sistema
```http
  GET /session/list
```
##### Dados de entrada

| Campo | Tipo  | Obrigatório | Local de entrada | Descrição |
|:-----:|:-----:|:-----------:|:----------------:|:---------:|
| `N/A` | `N/A` |     N/A     |       N/A        |    N/A    |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |                   Descrição                    |
|:--------------:|:---------:|:-----------:|:----------------------------------------------:|
|      `id`      | `integer` |     Sim     |     Código identificador da sessão criada      |
| `rulingTitle`  | `string`  |     Sim     |   Título da pauta na qual a sessão pertence    |
|   `rulingId`   | `integer` |     Sim     | Código identificador na qual a sessão pertence |
|   `duration`   | `integer` |     Sim     |         Duração da sessão em segundos          |
| `creationDate` | `string`  |     Sim     |           Data de criação da sessão            |

#### Listar sessão por código identificador
Endpoint para realizar a busca de uma sessão específica por meio de seu código identificador
```http
  GET /session/list/{sessionId}
```
##### Dados de entrada

|    Campo    |   Tipo    | Obrigatório | Local de entrada |           Descrição            |
|:-----------:|:---------:|:-----------:|:----------------:|:------------------------------:|
| `sessionId` | `integer` |     Sim     |       Path       | Código identificador da sessão |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |                   Descrição                    |
|:--------------:|:---------:|:-----------:|:----------------------------------------------:|
|      `id`      | `integer` |     Sim     |     Código identificador da sessão criada      |
| `rulingTitle`  | `string`  |     Sim     |   Título da pauta na qual a sessão pertence    |
|   `rulingId`   | `integer` |     Sim     | Código identificador na qual a sessão pertence |
|   `duration`   | `integer` |     Sim     |         Duração da sessão em segundos          |
| `creationDate` | `string`  |     Sim     |           Data de criação da sessão            |

### Votos
#### Computar um voto
Endpoint para computar um voto de um associado em uma sessão.
```http
  POST /vote/compute
```
##### Dados de entrada

|    Campo    |   Tipo    | Obrigatório | Local de entrada |                                Descrição                                |
|:-----------:|:---------:|:-----------:|:----------------:|:-----------------------------------------------------------------------:|
|    `cpf`    | `string`  |     Sim     |       Body       |                    CPF do associado que está votando                    |
|   `vote`    | `boolean` |     Sim     |       Body       | Voto do associado. True corresponde à "SIM" e False corresponde à "Não" |
| `sessionId` | `integer` |     Sim     |       Body       |     Código identificador da sessão em que o associado está votando.     |

##### Dados de retorno

|     Campo      |   Tipo    | Obrigatório |                   Descrição                    |
|:--------------:|:---------:|:-----------:|:----------------------------------------------:|
|    `voteId`    | `integer` |     Sim     |     Código identificador do voto computado     |
|  `sessionId`   | `integer` |     Sim     |     Código identificador da sessão votada      |
| `cpfAssociate` | `string`  |     Sim     |           CPF do associado que votou           |
| `computedVote` | `string`  |     Sim     |     Dado do voto do usuário "Sim" ou "Não"     |
| `sessionDate`  | `string`  |     Sim     |           Data de criação da sessão            |
|    `topic`     | `string`  |     Sim     | Título da pauta em que a sessão estava votando |

### CPF Facade
#### Validar CPF pela facade
Endpoint para validar o CPF pela facade
```http
  POST /facade/{cpf}/validate}
```
##### Dados de entrada

|    Campo    |   Tipo    | Obrigatório | Local de entrada |     Descrição      |
|:-----------:|:---------:|:-----------:|:----------------:|:------------------:|
|    `cpf`    | `string`  |     Sim     |       Path       | CPF para validação |

##### Dados de retorno

|  Campo   |   Tipo   | Obrigatório |                   Descrição                   |
|:--------:|:--------:|:-----------:|:---------------------------------------------:|
| `status` | `string` |     Sim     | Status que indica se CPF é válido ou inválido |