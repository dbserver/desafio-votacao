# Projeto Desafio Votacao

O Projeto Desafio Votação é uma aplicação desenvolvida como parte de um desafio, que tem como objetivo simular um sistema de votação em pautas em uma cooperativa.

## Ambiente de Desenvolvimento

    - Windows 10 Pro

## Tecnologias utilizadas

    - Java 17
    - Spring Boot
    - Swagger
    - JPA (Java Persistence API)
    - PostgreSQL
    - Docker
    - jacoco (Controle da cobertura de test)
    - SonarLint
    - Junit5 (Unit Test)
    - Mockito (Unit Test)

## Configuração do ambiente

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

    - JDK 17
    - Maven
    - Docker Desktop (para Windows)

## Instalando o Docker no Windows

    Baixe o Docker Desktop para Windows no site oficial do Docker: https://docs.docker.com/desktop/install/windows-install/
    Execute o instalador baixado e siga as instruções para concluir a instalação.
    Após a instalação, inicie o Docker Desktop.

## Baixando a imagem PostgreSQL mais atualizada

Execute o seguinte comando no terminal para baixar a imagem PostgreSQL mais atualizada do Docker Hub:

shell
      
      docker pull postgres:latest

## Executando a aplicação

1. Clone o repositório para sua máquina:

shell

    git clone https://github.com/seu-usuario/desafio-votacao.git

2. Navegue até o diretório do projeto:

shell

    cd desafio-votacao

3. Configure as informações de conexão com o banco de dados PostgreSQL no arquivo application.yml localizado em
   src/main/resources:

yaml

    spring:
        datasource:
            url: jdbc:postgresql://localhost:5432/cooperativismo
            username: user_test
            password: 12345

Ou

    spring:
        datasource:
            url: ${SPRING_DATASOURCE_URL}
            username: ${POSTGRES_USER}
            password: ${POSTGRES_PASSWORD}

Não esqueça de colocar as variáveis de ambiente na sua IDE.

    POSTGRES_PASSWORD=12345
    POSTGRES_USER=user_test
    SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/cooperativismo

4. Execute o Docker Desktop e o seguinte comando para construir e executar a aplicação:


      abra o docker desktop no seu windowns, na pasta raiz da sua aplicação.

shell

      docker-compose up -d

5. Execute o seguinte comando para construir e executar a aplicação, usando o comando
abaixo e depois dê play na aplicação.

shell

        mvn spring-boot:run

6. Acesse a documentação da API no Swagger pelo seguinte endereço:

http://localhost:8080/swagger-ui.html

## Endpoints da API

A API disponibiliza os seguintes endpoints para gerenciar associados, pautas, sessões de votação e votos:

### Associados
#### Cadastrar Associado

      Método: POST
      URL: '/api/public/v1/associados/cadastrar'
      Descrição: Cadastra um novo associado.
      Parâmetros:
      'nome' (obrigatório, tipo: String): O nome do associado.
      'cpf' (obrigatório, tipo: String): O CPF do associado.
      Resposta de Sucesso (HTTP 201 - Created):
      Corpo: "Associado cadastrado com sucesso."
      Resposta de Erro (HTTP 400 - Bad Request):
      Corpo: Mensagem de erro informando o motivo da falha na validação dos parâmetros.
      Resposta de Erro (HTTP 409 - Conflict):
      Corpo: "Já existe um associado com esse CPF."
      Resposta de Erro (HTTP 500 - Internal Server Error):
      Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."



#### Listar Associados

     Método: GET
     URL: '/api/public/v1/associados/listar'
     Descrição: Lista todos os associados cadastrados.
     Resposta de Sucesso (HTTP 200 - OK):
     Corpo: Lista de objetos AssociadoDto contendo os dados dos associados cadastrados.
     Resposta de Sucesso (HTTP 404 - Not Found):
     Caso não haja associados cadastrados, a resposta será vazia.
     Resposta de Erro (HTTP 500 - Internal Server Error):
     Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."

### Pautas

#### Cadastrar Pauta

      Método: POST
      URL: '/api/public/v1/pautas/cadastrar'
      Descrição: Cadastra uma nova pauta.
      Parâmetros:
      'titulo' (obrigatório, tipo: String): O título da pauta.
      'descricao' (obrigatório, tipo: String): A descrição da pauta.
      'tempoSessao' (opcional, tipo: Integer, padrão: 1): O tempo de duração da sessão de votação em minutos.
      Resposta de Sucesso (HTTP 201 - Created):
      Corpo: "Pauta cadastrada com sucesso."
      Resposta de Erro (HTTP 400 - Bad Request):
      Corpo: Mensagem de erro informando o motivo da falha na validação dos parâmetros.
      Resposta de Erro (HTTP 409 - Conflict):
      Corpo: "Já existe uma pauta com a sessão aberta."
      Resposta de Erro (HTTP 500 - Internal Server Error):
      Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."

#### Listar Pautas

      Método: GET
      URL: '/api/public/v1/pautas/listar'
      Descrição: Lista todas as pautas cadastradas.
      Resposta de Sucesso (HTTP 200 - OK):
      Corpo: Lista de objetos PautaDto contendo os dados das pautas cadastradas.
      Resposta de Sucesso (HTTP 404 - Not Found):
      Caso não haja pautas cadastradas, a resposta será vazia.
      Resposta de Erro (HTTP 500 - Internal Server Error):
      Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."

#### Obter Resultado da Votação

      Método: GET
      URL: '/api/public/v1/pautas/resultado-votacao/{pautaId}'
      Descrição: Obtém o resultado da votação de uma pauta específica.
      Parâmetros:
      'pautaId' (obrigatório, tipo: Long): O ID da pauta.
      Resposta de Sucesso (HTTP 200 - OK):
      Corpo: Objeto ResultadoVotacaoDto contendo os resultados da votação da pauta.
      Resposta de Erro (HTTP 404 - Not Found):
      Corpo: "Pauta não encontrada."
      Resposta de Erro (HTTP 500 - Internal Server Error):
      Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."

### Sessões de Votação

#### Listar Sessões de Votação

      Método: GET
      URL: '/api/public/v1/sessoes/listar'
      Descrição: Lista todas as sessões de votação cadastradas.
      Resposta de Sucesso (HTTP 200 - OK):
      Corpo: Lista de objetos SessaoDto contendo os dados das sessões de votação cadastradas.
      Resposta de Sucesso (HTTP 404 - Not Found):
      Caso não haja sessões de votação cadastradas, a resposta será vazia.
      Resposta de Erro (HTTP 500 - Internal Server Error):
      Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."

### Votos

#### Registrar Voto

      Método: POST
      URL: '/api/public/v1/votos/votar'
      Descrição: Registra um novo voto.
      Parâmetros:
      'voto' (obrigatório, tipo: VotoEnum): O voto registrado, que pode ser 'SIM' ou 'NAO'.
      'cpfAssociado' (obrigatório, tipo: String): O CPF do associado que está registrando o voto.
      Resposta de Sucesso (HTTP 201 - Created):
      Corpo: "Voto registrado com sucesso."
      Resposta de Erro (HTTP 400 - Bad Request):
      Corpo: Mensagem de erro informando o motivo da falha na validação dos parâmetros.
      Resposta de Erro (HTTP 409 - Conflict):
      Corpo: "Associado já votou nessa pauta."
      Resposta de Erro (HTTP 404 - Not Found):
      Corpo: "Não existe uma sessão aberta." ou "Associado não cadastrado para efetuar a votação."
      Resposta de Erro (HTTP 500 - Internal Server Error):
      Corpo: "Erro ao acessar o banco de dados." ou "Erro interno."

## Contribuição

Contribuições para a melhoria deste projeto são sempre bem-vindas.

## Licença

Este projeto está licenciado sob a MIT License.