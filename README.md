# vote-changelle API

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução para dispositivos móveis para gerenciar e participar dessas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por
  um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
  é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta


### Para rodar o banco de dados via docker
1. rodar comando "docker-compose up -d"

### Para rodar a aplicação

1. rodar "./gradlew clean build"
2. rodar "./gradlew bootRun"


### Técnologias utilizadas

* Java 17
* Gradle
* Postgres
* SpringBoot
* Lombok
* Flyway

## Documentação

Api documentada com o Swagger:http://localhost:8081/api/swagger-ui/index.html


