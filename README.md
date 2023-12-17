# Votação
* Este projeto consiste na construção de um Back-End em Java e Spring Boot para gerenciar uma votação de pauta e a contabilização de votos.

# Requerimentos
* Maven 3
* Java JDK 17
* Banco de dados PostgreSQL (A partir da versão 15)

# Como rodar
1. Configure seu banco de dados de acordo com as informações definidas no arquivo "application.properties":
```
spring.datasource.url=jdbc:postgresql://localhost:5432/votation
spring.datasource.username=postgres
spring.datasource.password=postgres
```
2. Via CMD, navegue até a pasta principal do projeto (Exemplo: C:\dev\votation)
3. Para gerar um arquivo .jar e rodar os testes:
```
mvn package
```
4. Para rodar a aplicação:
```
java -jar target/votation-0.0.1-SNAPSHOT.jar
```
* Ou (rodar usando Maven):
```
mvn spring-boot:run
```
# Documentação das operações
* A documentação das operações pode ser acessada através do link:

http://localhost:8080/swagger-ui/index.html

A documentação inclui informações como descrição das operações, descrição dos campos, campos obrigatórios, corpo da requisição e resposta.

# Resposta Tarefas Bônus

### Versionamento da API
* Para falar sobre o versionamento de uma API é necessário entender o objetivo dessa nova versão. 
* É importante manter a API atual em funcionamento até a nova versão ser liberada. 
* Na minha visão um bom modo de realizar esse tipo de tarefa é utilizando a URL da operação, seguindo uma sequencia das versões, por exemplo:
#### Versão atual:
````
/api/v1/agendas
````
#### Nova versão:
````
/api/v2/agendas
````
* Também existem outros tipos de versionamento, como exemplo, pelo Header da requisição, utilizando um parâmetro para identificar a versão utilizada da API, exemplo:
````
api-version: 2
````
# Futuras Melhorias:
* Validar dados do associado
* Criar grupos de associados, validar se o grupo pode votar a pauta
* Internacionalização das mensagens de erro e regras de negócio
* Melhorar validações, como exemplo: tamanho maximo do nome da pauta
