# sistema-votacao-de-pauta

O sistema permite: cadastrar uma nova pauta, iniciar uma nova sessão, votar e consultar o resultado da votação.

## Tecnologias
Para construção do sistema foram utilizadas as tecnologias:
- Java 17;
- Spring Boot Framework;
- Docker
- ReactJs;

## Rodar localmente

Com Java 17, Maven, Docker e node instalados, siga as instruções:

Backend:
```sh
$ git clone https://github.com/Fernando-Fritzen/desafio-votacao.git
$ cd desafio-votacao/backend/database-docker
$ docker-compose up -d
$ cd ..
$ mvnw spring-boot:run
```
Frontend:
```sh
$ cd desafio-votacao/frontend
$ npm install
$ npm start
```

A api ficará disponível localmente em [localhost:8080](http://localhost:8080/).

O frontend será executado localmente em [localhost:3000](http://localhost:3000/).
