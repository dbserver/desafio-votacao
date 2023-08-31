# votacao-api
___________________________________________________________________________________
Este sistema tem como objetivo criar e gerenciar a votação de usuários associados.

### Dependências

* Java 17

### Para rodar a aplicação

1. rodar "./gradlew clean build"
2. rodar java "java -jar build/libs/votacao-api-0.0.1-SNAPSHOT.jar"


### Técnologias utilizadas

* Java 17
* Gradle
* H2
* Postgres
* SpringBoot
* Lombok

## Configuração

### Configuração do banco de dados:

Configure sua conexão de banco de dados PostgreSQL em 
src/main/resources/application.yml.

![img.png](img.png)

### Uso

#### Usando Docker Compose para PostgreSQL
Execute o seguinte comando para criar e  iniciar o contêiner PostgreSQL:

`docker run --name desafio-votacao -p 5432:5432  -e POSTGRES_PASSWORD=1234 -d postgres`

Seu banco de dados PostgreSQL agora deve estar em execução em um contêiner Docker.

## Documentação

Api documentada com o Swagger.

Para ver acesse:
http://localhost:8080/swagger-ui.html#/






